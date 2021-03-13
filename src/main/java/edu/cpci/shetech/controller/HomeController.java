package edu.cpci.shetech.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	
	@RequestMapping(value =  {"/","/homePage"}, method = RequestMethod.GET)
	public String HomePage (Model model) {
		System.out.println("HOMEPAGE CONTROLLER");
		return "homePage.html";
	}
	
	@RequestMapping(value = ("/login"), method = RequestMethod.GET)
    public String loginPage(Model model) {
		System.out.println("LOGUIN CONTROLLER");
        return "login.html";
    }
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		String message = "No posee permisos suficientes para acceder a esta página!";
		model.addAttribute("message", message);
		return "404page";
	}
}
