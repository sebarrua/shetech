package edu.cpci.shetech.utils;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.UsuarioService;

@Service
public class VistaUtils {
	
	@Autowired
	private UsuarioService usuarioService;
	
	public void setHeader(Principal principal, ModelMap model) {
		String noAdmin="noadmin";
		if(principal!=null) {
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			if(!userLog.getUsername().equals("admin")) {
				model.addAttribute("isAdmin", noAdmin);
			}
			model.addAttribute("userLog", userLog.getUsername());
			model.addAttribute("username", principal.getName());
		}else {
			model.addAttribute("isAdmin", noAdmin);
			model.addAttribute("userNoLog", "noUser");
		}
		
	}

}
