package hr.spring.fina.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nastavnik")
public class Nastavnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_nastavnik;

	@Column
	private String prezime;

	@Column
	private String ime;

	@Column
	private String email;

	@Column
	private String tel;
	
	public long getId_nastavnik() {
		return id_nastavnik;
	}

	public void setId_nastavnik(long id_nastavnik) {
		this.id_nastavnik = id_nastavnik;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
