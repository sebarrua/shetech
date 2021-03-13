package edu.cpci.shetech.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.UsuarioService;

@Controller
@RequestMapping(value="/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = ("/miPerfil"))
	public String MiPrefil (@ModelAttribute ("Usuario") Usuario usuario, Model model, Principal principal) {
		Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
		model.addAttribute("Usuario", userLog);
		return "miPerfilPage.html";
	}

}
