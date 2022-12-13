package hr.spring.fina.service;

import hr.spring.fina.model.Kolegij;

public interface KolegijService {

	Kolegij createKolegij(Kolegij Kolegij);

	Kolegij updateKolegij(Kolegij Kolegij);

	Iterable<Kolegij> getAllKolegij();
	
	Iterable<Kolegij> getKolegijStudij(String studij);
	
	Kolegij getKolegij(long id_Kolegij);

	void deleteKolegij(long id);

}
