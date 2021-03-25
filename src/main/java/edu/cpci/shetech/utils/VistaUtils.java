package edu.cpci.shetech.utils;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.UsuarioService;

@Service
public class VistaUtils {
	
	@Autowired
	private UsuarioService usuarioService;
	
	public void setHeader(Principal principal, Model model) {
		
		if(principal!=null) {
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			model.addAttribute("userLog", userLog.getUsername());
			model.addAttribute("username", principal.getName());
		}else {
			model.addAttribute("userNoLog", "noUser");
		}
		
	}

}
