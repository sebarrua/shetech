package edu.cpci.shetech.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.UsuarioService;

@Controller
public class HomeController {
	
	@Autowired
	private UsuarioService usuarioService;

	
	
	@RequestMapping(value =  {"/","/homePage"}, method = RequestMethod.GET)
	public String HomePage (Model model, Principal principal) {
		System.out.println("HOMEPAGE CONTROLLER");
		
		if(principal!=null) {
			System.out.println("USUARIO LOGUEADO: "+principal.getName());
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			model.addAttribute("userLog", userLog.getUsername());
		}else {
			System.out.println("NO HAY PRINCIPAL");
			model.addAttribute("userNoLog", "noUser");
		}
		return "homePage.html";
	}
	
	@RequestMapping(value = ("/login"), method = RequestMethod.GET)
    public String loginPage(Model model, Principal principal) {
		System.out.println("LOGUIN CONTROLLER");
		if(principal!=null) {
			System.out.println("USUARIO LOGUEADO: "+principal.getName());
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			model.addAttribute("userLog", userLog.getUsername());
		}else {
			System.out.println("NO HAY PRINCIPAL");
			model.addAttribute("userNoLog", "noUser");
		}
        return "login.html";
    }
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model,Principal principal) {
		String message = "No posee permisos suficientes para acceder a esta página!";
		model.addAttribute("message", message);
		if(principal!=null) {
			System.out.println("USUARIO LOGUEADO: "+principal.getName());
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			model.addAttribute("userLog", userLog.getUsername());
		}else {
			System.out.println("NO HAY PRINCIPAL");
			model.addAttribute("userNoLog", "noUser");
		}
		return "403page";
	}
	
}
