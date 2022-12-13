package hr.spring.fina.service;

import hr.spring.fina.model.Obavijest;

public interface ObavijestiService {

	Obavijest createEntity(Obavijest entityData);

	Obavijest updateEntity(Obavijest entityData);
	
	Iterable<Obavijest> getAllEntityByFakultet(long id_fakultet);
	
	Iterable<Obavijest> getAllEntityByFakultetStudij(long id_fakultet, long id_studij);

	Obavijest getEntityById(long entityId);

	void deleteEntity(long entityId);

}
