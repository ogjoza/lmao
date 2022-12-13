package hr.spring.fina.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.spring.fina.exceptions.ResourceNotFoundException;
import hr.spring.fina.model.Student;
import hr.spring.fina.repository.StudentRepository;



@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
	
	@Override
	public Student updateStudent(Student student) throws ResourceNotFoundException {
		Optional<Student> productDb = this.studentRepository.findById(student.getId_student());

		if (productDb.isPresent()) {
			Student studentUpdate = productDb.get();
			studentUpdate.setId_student(student.getId_student());
			studentUpdate.setPrezime(student.getPrezime());
			studentUpdate.setIme(student.getIme());
			studentRepository.save(studentUpdate);
			return studentUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + student.getId_student());
		}
	}

	@Override
	public Iterable<Student> getAllStudents() {
		return this.studentRepository.findAll();
	}

	@Override
	public Iterable<Student> getStudentiIme(String ime) {
		return this.studentRepository.findStudentsByName(ime); 
	}
	
	@Override
	public Iterable<Student> getStudentiGodina(int godina) {
		return this.studentRepository.findStudentsByGodinaStudija(godina); 
	}


	@Override
	public void deleteStudent(long studentId) {
		Optional<Student> productDb = this.studentRepository.findById(studentId);

		if (productDb.isPresent()) {
			this.studentRepository.delete(productDb.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen.");
		}

	}
	
	@Override
	public Student getStudent(long studentId) {

		if (studentId == 0)
			return new Student();
		Optional<Student> productDb = this.studentRepository.findById(studentId);

		if (productDb.isPresent()) {
			return productDb.get();
		} else {
			return new Student();
		}
	}


}
