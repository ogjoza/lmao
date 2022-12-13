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

import hr.spring.fina.model.Nastavnik;
import hr.spring.fina.service.NastavnikService;

@Controller
@RequestMapping("/nastavnik")
public class NastavnikController {

	@Autowired   
    private NastavnikService service;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		ArrayList<Nastavnik> popisNastavnika = (ArrayList)service.getAllNastavnik();
		model.addAttribute("listNastavnika", popisNastavnika);
		
		return "pocetnaNastavnici";
	}
	
	@RequestMapping(value = "/novi", method = RequestMethod.GET)
	public String showNewNastavnikPage(Model model) {
		Nastavnik nastavnik = new Nastavnik();
		model.addAttribute("nastavnik", nastavnik);
		
		return "novi_nastavnik";
	}
	
	@RequestMapping(value = "/novi", method = RequestMethod.POST)
	public String showNewNastavnikPage2(@ModelAttribute("nastavnik") Nastavnik nastavnik) {
		service.createNastavnik(nastavnik);
		return "redirect:/nastavnik/";
	}
	
	
	@RequestMapping("/uredi/{id}")
	public ModelAndView showEditNastavnikPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("uredi_nastavnik");
		Nastavnik nastavnik = service.getNastavnik(id);
		mav.addObject("nastavnik", nastavnik);
		
		return mav;
	}
	
	@RequestMapping(value = "/uredi", method = RequestMethod.POST)
	public String saveNastavnik(@ModelAttribute("nastavnik") Nastavnik nastavnik) {
		service.updateNastavnik(nastavnik);
		
		return "redirect:/nastavnik/";
	}
	
	@RequestMapping("/brisi/{id}")
	public String brisiNastavnik(@PathVariable(name = "id") int id) {
		service.deleteNastavnik(id);
		return "redirect:/nastavnik/";		
	}
}
