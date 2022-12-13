package hr.spring.fina.service;

import hr.spring.fina.model.Nastavnik;

public interface NastavnikService {

	Nastavnik createNastavnik(Nastavnik Nastavnik);

	Nastavnik updateNastavnik(Nastavnik Nastavnik);

	Iterable<Nastavnik> getAllNastavnik();
	
	Nastavnik getNastavnik(long id_Nastavnik);

	void deleteNastavnik(long id);

}
