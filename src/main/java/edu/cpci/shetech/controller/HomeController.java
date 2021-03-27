package edu.cpci.shetech.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.UsuarioService;
import edu.cpci.shetech.utils.VistaUtils;

@Controller
public class HomeController { 
	
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private VistaUtils vistaUtils;

	
	
	@RequestMapping(value =  {"/","/homePage"}, method = RequestMethod.GET)
	public String HomePage (ModelMap model, Principal principal) {
		System.out.println("HOMEPAGE CONTROLLER");
		
		this.vistaUtils.setHeader(principal, model);
		return "homePage.html";
	}
	
	@RequestMapping(value = ("/login"), method = RequestMethod.GET)
    public String loginPage(ModelMap model, Principal principal) {
		System.out.println("LOGUIN CONTROLLER");
		this.vistaUtils.setHeader(principal, model);
        return "login.html";
    }
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(ModelMap model,Principal principal) {
		String message = "¡Lo sentimos! No posee permisos suficientes para acceder a esta página.";
		model.addAttribute("message", message);
		this.vistaUtils.setHeader(principal, model);
		return "403";
	}
	
}
