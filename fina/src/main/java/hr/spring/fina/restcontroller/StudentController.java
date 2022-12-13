package hr.spring.fina.restcontroller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hr.spring.fina.model.ResponseData;
import hr.spring.fina.model.Student;
import hr.spring.fina.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/sviStudenti")
	public ResponseEntity<Iterable<Student>> getAllStudents() {
		return ResponseEntity.ok().body(this.studentService.getAllStudents());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Student> getProductById(@PathVariable long id) {
		return ResponseEntity.ok().body(this.studentService.getStudent(id));
	}

	@PostMapping("/")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		return ResponseEntity.ok().body(this.studentService.createStudent(student));
	}

	@PutMapping("/")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		return ResponseEntity.ok().body(this.studentService.updateStudent(student));
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteStudent2b(@PathVariable long id) {
		try {
			this.studentService.deleteStudent(id);
			return HttpStatus.OK;
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}

	}

	// API
	@RequestMapping(value = "/api/", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ArrayList<Student> getAllEntitiesArray() {
		return (ArrayList<Student>) this.studentService.getAllStudents();
	}

	@RequestMapping(value = "/api/upisStudenta", method = RequestMethod.POST)
	public ResponseEntity<ResponseData> upisStudenta(@RequestBody Student dataStudent) throws IOException {
		ResponseData RD = new ResponseData();
		try {

			this.studentService.createStudent(dataStudent);
			RD.setErrorCode(200);
			RD.setMessage("Uspješno upisan podatak");
			return ResponseEntity.status(200).body(RD);
		} catch (Exception err) {
			RD.setErrorCode(403);
			RD.setMessage("Pogreška kod upisa podataka");
			return ResponseEntity.status(403).body(RD);
		}
	}

	@RequestMapping(value = "/api/obnoviStudent", method = RequestMethod.PUT)
	public ResponseEntity<ResponseData> obnoviStudenta(@RequestBody Student dataStudent) throws IOException {
		ResponseData RD = new ResponseData();
		try {

			this.studentService.updateStudent(dataStudent);
			RD.setErrorCode(200);
			RD.setMessage("Uspješno obnovljen podatak");
			return ResponseEntity.status(200).body(RD);
		} catch (Exception err) {
			RD.setErrorCode(403);
			RD.setMessage("Pogreška prilikom obnavljanja podataka");
			return ResponseEntity.status(403).body(RD);
		}
	}

	@RequestMapping(value = "/api/obrisiStudenta", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData> brisiStudenta(@RequestBody Student dataStudent) throws IOException {
		ResponseData RD = new ResponseData();
		try {

			this.studentService.deleteStudent(dataStudent.getId_student());
			RD.setErrorCode(200);
			RD.setMessage("Podaci o studentu uspješno su obrisani");
			return ResponseEntity.status(200).body(RD);
		} catch (Exception err) {
			RD.setErrorCode(403);
			RD.setMessage("Pogreška prilikom brisanja podataka");
			return ResponseEntity.status(403).body(RD);
		}
	}

}
