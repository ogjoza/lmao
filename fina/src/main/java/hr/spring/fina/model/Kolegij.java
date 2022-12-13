package hr.spring.fina.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kolegij")
public class Kolegij {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_kolegija;

	@Column
	private String naziv;

	@Column
	private int ECTS;

	@Column
	private String studij;

	
	public Kolegij() {
		super();
	}

	public Kolegij(long id_kolegija, String naziv, int eCTS, String studij) {
		super();
		this.id_kolegija = id_kolegija;
		this.naziv = naziv;
		ECTS = eCTS;
		this.studij = studij;
	}

	public long getId_kolegija() {
		return id_kolegija;
	}

	public void setId_kolegija(long id_kolegija) {
		this.id_kolegija = id_kolegija;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getECTS() {
		return ECTS;
	}

	public void setECTS(int eCTS) {
		ECTS = eCTS;
	}

	public String getStudij() {
		return studij;
	}

	public void setStudij(String studij) {
		this.studij = studij;
	}
	
	public ArrayList<Kolegij> getPopisKolegija(){
		ArrayList<Kolegij> popis= new ArrayList<>();
		popis.add(new Kolegij(1,"Programiraje", 6, "Računarstvo"));
		popis.add(new Kolegij(2,"Engleski", 4, "Računarstvo"));
		popis.add(new Kolegij(3,"Algoritmi", 7, "Računarstvo"));
		popis.add(new Kolegij(4,"Matematika", 6, "Menadžment"));
		return popis;
	}

}
