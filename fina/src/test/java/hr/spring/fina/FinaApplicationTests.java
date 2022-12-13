package hr.spring.fina;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;

import hr.spring.fina.model.Student;
import hr.spring.fina.repository.StudentRepository;
import hr.spring.fina.restcontroller.StudentController;
import hr.spring.fina.service.StudentService;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
//@DataJpaTest
@AutoConfigureMockMvc
@WebMvcTest(StudentController.class)
class FinaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@MockBean
	private StudentRepository studentRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
    public void init() {
		Student student = new Student(1L, "Mihalic", "Darko", "Prvi");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
    }

	
	@Test
	public void testUpisStudenta() throws Exception {

		Student s = new Student("Novak", "Anita", "Prva");
		mockMvc.perform(
				post("/student/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(s)))
				.andExpect(status().is(200)).andDo(print());

	}

	
	@Test
	void testGetPopisStudenata() throws Exception {
		ArrayList<Student> popis = new ArrayList<>();
		popis.add(new Student(1, "Novak", "Ana", "Prva"));
		popis.add(new Student(2, "Horavat", "Vanja", "Druga"));
		popis.add(new Student(3, "Markovic", "Ines", "Treca"));

		when(studentService.getAllStudents()).thenReturn(popis);
		mockMvc.perform(get("/student/sviStudenti")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(popis.size())).andDo(print());
	}

	
	@Test
	void testUpdateStudent() throws Exception {
		long id = 1L;
		Student student = new Student(id, "Bošnjak", "Ivica", "Prvi");
		Student studentUpdate = new Student(id, "Bošnjak", "Ivica", "Drugi");

		when(studentRepository.findById(id)).thenReturn(Optional.of(student));
		when(studentRepository.save(student)).thenReturn(studentUpdate);

		//when(studentService.getStudent(id)).thenReturn(student);
		//when(studentService.createStudent(student)).thenReturn(studentUpdate);
		mockMvc.perform(put("/student/").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(studentUpdate))).andExpect(status().isOk()).andDo(print());
	}

	@Test
	void testBrisanjeStudenta() throws Exception {
		long id = 10L;
		doNothing().when(studentRepository).deleteById(id);
		mockMvc.perform(delete("/student/{id}", id)).andExpect(status().isOk()).andDo(print());
	}
	
	
	@Test
    public void testBrisanjeStudenta2() throws Exception{
        // given - precondition or setup
		Student student = new Student(4,"Novak", "Andrija", "Treći");
		studentRepository.save(student);

        ResultActions response = mockMvc.perform(delete("/student/{id}", student.getId_student()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }

}
