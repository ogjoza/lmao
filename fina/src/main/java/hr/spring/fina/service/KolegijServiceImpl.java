package hr.spring.fina.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.spring.fina.exceptions.ResourceNotFoundException;
import hr.spring.fina.model.Kolegij;
import hr.spring.fina.repository.KolegijRepository;



@Service
@Transactional
public class KolegijServiceImpl implements KolegijService {

	@Autowired
	private KolegijRepository kolegijRepository;

	@Override
	public Kolegij createKolegij(Kolegij Kolegij) {
		return kolegijRepository.save(Kolegij);
	}
	
	@Override
	public Kolegij updateKolegij(Kolegij dataKolegij) throws ResourceNotFoundException {
		Optional<Kolegij> productDb = this.kolegijRepository.findById(dataKolegij.getId_kolegija());
		if (productDb.isPresent()) {
			Kolegij kolegijUpdate = productDb.get();
			kolegijUpdate.setNaziv(dataKolegij.getNaziv());
			kolegijUpdate.setECTS(dataKolegij.getECTS());
			kolegijUpdate.setStudij(dataKolegij.getStudij());
			kolegijRepository.save(kolegijUpdate);
			return kolegijUpdate;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + dataKolegij.getId_kolegija());
		}
	}

	@Override
	public Iterable<Kolegij> getAllKolegij() {
		return this.kolegijRepository.findAll();
	}
	
	@Override
	public Iterable<Kolegij> getKolegijStudij(String studij) {
		return this.kolegijRepository.odrediKolegijaPremaStudiju(studij);
	}

	
	@Override
	public Kolegij getKolegij(long KolegijId) {

		if (KolegijId == 0)
			return new Kolegij();
		Optional<Kolegij> productDb = this.kolegijRepository.findById(KolegijId);

		if (productDb.isPresent()) {
			return productDb.get();
		} else {
			return new Kolegij();
		}
	}
	
	@Override
	public void deleteKolegij(long KolegijId) {
		Optional<Kolegij> productDb = this.kolegijRepository.findById(KolegijId);

		if (productDb.isPresent()) {
			this.kolegijRepository.delete(productDb.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen.");
		}

	}


}
