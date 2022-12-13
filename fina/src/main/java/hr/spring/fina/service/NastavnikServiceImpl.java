package hr.spring.fina.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.spring.fina.exceptions.ResourceNotFoundException;
import hr.spring.fina.model.Nastavnik;
import hr.spring.fina.repository.NastavnikRepository;

@Service
@Transactional
public class NastavnikServiceImpl implements NastavnikService {

	@Autowired
	private NastavnikRepository NastavnikRepository;

	@Override
	public Nastavnik createNastavnik(Nastavnik Nastavnik) {
		return NastavnikRepository.save(Nastavnik);
	}

	@Override
	public Nastavnik updateNastavnik(Nastavnik dataNastavnik) throws ResourceNotFoundException {
		Optional<Nastavnik> productDb = this.NastavnikRepository.findById(dataNastavnik.getId_nastavnik());
		if (productDb.isPresent()) {
			Nastavnik nastavnikUpdate = productDb.get();
			nastavnikUpdate.setIme(dataNastavnik.getIme());
			nastavnikUpdate.setPrezime(dataNastavnik.getPrezime());
			nastavnikUpdate.setEmail(dataNastavnik.getEmail());
			nastavnikUpdate.setTel(dataNastavnik.getTel());
			NastavnikRepository.save(nastavnikUpdate);
			return nastavnikUpdate;
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen : " + dataNastavnik.getId_nastavnik());
		}
	}

	@Override
	public Iterable<Nastavnik> getAllNastavnik() {
		return this.NastavnikRepository.findAll();
	}

	@Override
	public Nastavnik getNastavnik(long NastavnikId) {

		if (NastavnikId == 0)
			return new Nastavnik();
		Optional<Nastavnik> productDb = this.NastavnikRepository.findById(NastavnikId);

		if (productDb.isPresent()) {
			return productDb.get();
		} else {
			return new Nastavnik();
		}
	}

	@Override
	public void deleteNastavnik(long NastavnikId) {
		Optional<Nastavnik> productDb = this.NastavnikRepository.findById(NastavnikId);

		if (productDb.isPresent()) {
			this.NastavnikRepository.delete(productDb.get());
		} else {
			throw new ResourceNotFoundException("Zapis nije pronađen.");
		}

	}

}
