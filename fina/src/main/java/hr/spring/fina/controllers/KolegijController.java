package hr.spring.fina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import hr.spring.fina.model.Kolegij;
import hr.spring.fina.service.KolegijService;

@Controller
@RequestMapping("/kolegij")
public class KolegijController {

	@Autowired   
    private KolegijService service;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		ArrayList<Kolegij> popisKolegija = (ArrayList)service.getAllKolegij();
		model.addAttribute("listKolegija", popisKolegija);
		
		return "pocetnaKolegiji";
	}
	
	@RequestMapping(value = "/novi", method = RequestMethod.GET)
	public String showNewKolegijPage(Model model) {
		Kolegij kolegij = new Kolegij();
		model.addAttribute("kolegij", kolegij);
		
		return "novi_kolegij";
	}
	
	@RequestMapping(value = "/novi", method = RequestMethod.POST)
	public String showNewKolegijPage2(@ModelAttribute("Kolegij") Kolegij kolegij) {
		service.createKolegij(kolegij);
		return "redirect:/kolegij/";
	}
	
	
	@RequestMapping("/uredi/{id}")
	public ModelAndView showEditKolegijPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("uredi_kolegij");
		Kolegij kolegij = service.getKolegij(id);
		mav.addObject("kolegij", kolegij);
		
		return mav;
	}
	
	@RequestMapping(value = "/uredi", method = RequestMethod.POST)
	public String saveKolegij(@ModelAttribute("kolegij") Kolegij kolegij) {
		service.updateKolegij(kolegij);
		
		return "redirect:/kolegij/";
	}
	
	@RequestMapping("/brisi/{id}")
	public String brisiKolegij(@PathVariable(name = "id") int id) {
		service.deleteKolegij(id);
		return "redirect:/kolegij/";		
	}
}
