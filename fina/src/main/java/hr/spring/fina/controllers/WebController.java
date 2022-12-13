package hr.spring.fina.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController {

	@RequestMapping(value ={"/", "/index"}, method = RequestMethod.GET)
	public ModelAndView pocetak(HttpSession request) {

		ModelAndView retVal = new ModelAndView();
		retVal.setViewName("index");
		return retVal; 
	}
	
	@RequestMapping(value ={"/adresa"}, method = RequestMethod.GET)
	public ModelAndView adresa(HttpSession request) {

		ModelAndView retVal = new ModelAndView();
		retVal.setViewName("adresa");
		return retVal; 
	}
	
}
