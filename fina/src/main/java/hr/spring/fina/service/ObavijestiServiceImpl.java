package hr.spring.fina.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.spring.fina.exceptions.ResourceNotFoundException;
import hr.spring.fina.model.Obavijest;
import hr.spring.fina.repository.ObavijestRepository;


@Service
@Transactional
public class ObavijestiServiceImpl implements ObavijestiService {

	static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	@Autowired
	private ObavijestRepository dataRepository;

	@Override
	public Obavijest createEntity(Obavijest data) {
		return dataRepository.save(data);
	}

	@Override
	public Obavijest updateEntity(Obavijest data) throws ResourceNotFoundException {
		Optional<Obavijest> productDb = this.dataRepository.findById(data.getId_obavijest());

		if (productDb.isPresent()) {
			Obavijest ObavijestUpdate = productDb.get();
			ObavijestUpdate.setNaslov(data.getNaslov());
			ObavijestUpdate.setTekst(data.getTekst());
			ObavijestUpdate.setSlika(data.getSlika());
			ObavijestUpdate.setId_studij(data.getId_studij());
			ObavijestUpdate.setNaziv_studija(data.getNaziv_studija());
			ObavijestUpdate.setDatum_objavljeno(data.getDatum_objavljeno());
			ObavijestUpdate.setDatum_objavljeno_tekst(data.getDatum_objavljeno_tekst());

			dataRepository.save(ObavijestUpdate);
			return ObavijestUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found.");
		}
	}

	@Override
	public Iterable<Obavijest> getAllEntityByFakultet(long id_fakultet) {
		return this.dataRepository.findAllByFakultet(id_fakultet);
	}
	
	@Override
	public Iterable<Obavijest> getAllEntityByFakultetStudij(long id_fakultet, long id_student) {
		return this.dataRepository.findAllByFakultetStudij(id_fakultet,id_student);
	}

	@Override
	public Obavijest getEntityById(long entityId) {

		if (entityId == 0)
			return new Obavijest();

		Optional<Obavijest> productDb = this.dataRepository.findById(entityId);

		if (productDb.isPresent()) {
			return productDb.get();
		} else {
			return new Obavijest();
		}
	}

	@Override
	public void deleteEntity(long entityId) {
		Optional<Obavijest> productDb = this.dataRepository.findById(entityId);

		if (productDb.isPresent()) {
			this.dataRepository.delete(productDb.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen");
		}

	}

}
