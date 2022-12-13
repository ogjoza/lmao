package hr.spring.fina.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "obavijest")
public class Obavijest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_obavijest;

	@Column
	private long id_fakultet; // oznaka fakulteta

	@Column(nullable = false)
	private String naslov;

	@Column(nullable = false)
	private String tekst;

	@Column
	private String slika;

	@Column
	private int id_studij; // oznaka studijskog programa

	@Column
	private String naziv_studija;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datum_objavljeno;

	@Column
	private String datum_objavljeno_tekst = "";

	public long getId_obavijest() {
		return id_obavijest;
	}

	public void setId_obavijest(long id_obavijest) {
		this.id_obavijest = id_obavijest;
	}

	public long getId_fakultet() {
		return id_fakultet;
	}

	public void setId_fakultet(long id_fakultet) {
		this.id_fakultet = id_fakultet;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public int getId_studij() {
		return id_studij;
	}

	public void setId_studij(int id_studij) {
		this.id_studij = id_studij;
	}

	public String getNaziv_studija() {
		return naziv_studija;
	}

	public void setNaziv_studija(String naziv_studija) {
		this.naziv_studija = naziv_studija;
	}

	public Date getDatum_objavljeno() {
		return datum_objavljeno;
	}

	public void setDatum_objavljeno(Date datum_objavljeno) {
		this.datum_objavljeno = datum_objavljeno;
	}

	public String getDatum_objavljeno_tekst() {
		return datum_objavljeno_tekst;
	}

	public void setDatum_objavljeno_tekst(String datum_objavljeno_tekst) {
		this.datum_objavljeno_tekst = datum_objavljeno_tekst;
	}

}
