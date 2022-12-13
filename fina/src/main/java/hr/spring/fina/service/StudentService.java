package hr.spring.fina.service;

import hr.spring.fina.model.Student;

public interface StudentService {

	Student createStudent(Student student);

	Student updateStudent(Student student);

	Iterable<Student> getAllStudents();

	Iterable<Student> getStudentiIme(String ime);
	
	Iterable<Student> getStudentiGodina(int godinaStudija);
	
	Student getStudent(long id_student);

	void deleteStudent(long id);

}
