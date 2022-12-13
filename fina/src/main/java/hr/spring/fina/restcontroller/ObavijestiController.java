package hr.spring.fina.restcontroller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hr.spring.fina.model.ResponseData;
import hr.spring.fina.model.Obavijest;
import hr.spring.fina.service.ObavijestiService;

@RestController
@RequestMapping("obavijesti")
public class ObavijestiController {

	@Autowired
	private ObavijestiService obavijestiService;

	// API
	@RequestMapping(value = "/api/sveObavijestiFakultet/{id_fakultet}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ArrayList<Obavijest> getAllEntitiesArray(@PathVariable long id_fakultet) {
		return (ArrayList<Obavijest>) this.obavijestiService.getAllEntityByFakultet(id_fakultet);
	}

	@RequestMapping(value = "/api/sveObavijestiFakultet/{id_fakultet}/{id_studij}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ArrayList<Obavijest> getAllEntitiesArray(@PathVariable long id_fakultet,
			@PathVariable long id_studij) {
		return (ArrayList<Obavijest>) this.obavijestiService.getAllEntityByFakultetStudij(id_fakultet, id_studij);
	}

	@RequestMapping(value = "/api/upisObavijesti", method = RequestMethod.POST)
	public ResponseEntity<ResponseData> upisObavijesti(@RequestBody Obavijest dataObavijest) throws IOException {
		ResponseData RD = new ResponseData();
		try {

			this.obavijestiService.createEntity(dataObavijest);
			RD.setErrorCode(200);
			RD.setMessage("Uspješno upisan podatak");
			return ResponseEntity.status(200).body(RD);
		} catch (Exception err) {
			RD.setErrorCode(403);
			RD.setMessage("Pogreška kod upisa podataka");
			return ResponseEntity.status(403).body(RD);
		}
	}

	@RequestMapping(value = "/api/obnoviObavijest", method = RequestMethod.PUT)
	public ResponseEntity<ResponseData> obnoviObavijest(@RequestBody Obavijest dataObavijest) throws IOException {
		ResponseData RD = new ResponseData();
		try {

			this.obavijestiService.updateEntity(dataObavijest);
			RD.setErrorCode(200);
			RD.setMessage("Uspješno obnovljen podatak");
			return ResponseEntity.status(200).body(RD);
		} catch (Exception err) {
			RD.setErrorCode(403);
			RD.setMessage("Pogreška prilikom obnavljanja podataka");
			return ResponseEntity.status(403).body(RD);
		}
	}

	@RequestMapping(value = "/api/obrisiObavijest", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData> brisiObavijest(@RequestBody Obavijest dataObavijest) throws IOException {
		ResponseData RD = new ResponseData();
		try {

			this.obavijestiService.deleteEntity(dataObavijest.getId_obavijest());
			RD.setErrorCode(200);
			RD.setMessage("Podaci o obavijesti uspješno su obrisani");
			return ResponseEntity.status(200).body(RD);
		} catch (Exception err) {
			RD.setErrorCode(403);
			RD.setMessage("Pogreška prilikom brisanja podatka.");
			return ResponseEntity.status(403).body(RD);
		}
	}

}
