package hr.spring.fina.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id_student;
	
	@Column
	private String prezime;
	
	@Column
	private String ime;
	
	@Column
	private String godinaStudija;


	public long getId_student() {
		return id_student;
	}

	public void setId_student(long id_student) {
		this.id_student = id_student;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(String godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	
	public Student() {
		super();
	}

	
	

	public Student(long id_student, String prezime, String ime, String godinaStudija) {
		super();
		this.id_student = id_student;
		this.prezime = prezime;
		this.ime = ime;
		this.godinaStudija = godinaStudija;
	}

	public Student( String prezime, String ime, String godinaStudija) {
		super();
		this.prezime = prezime;
		this.ime = ime;
		this.godinaStudija = godinaStudija;
	}
	

}
