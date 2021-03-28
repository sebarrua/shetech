package edu.cpci.shetech.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.UsuarioService;
import edu.cpci.shetech.utils.VistaUtils;

@Controller
//@RequestMapping(value="/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private VistaUtils vistaUtils;
	
	@GetMapping(value = ("/miPerfil"))
	public String MiPrefil (@ModelAttribute ("Usuario") Usuario usuario, Model model, Principal principal) {
		Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
		model.addAttribute("Usuario", userLog);
		
		return "miPerfilPage.html";
	}
	
	@PostMapping(value="/registerSave")
	public String RegisterSave (@ModelAttribute ("Usuario") Usuario usuario, 
								@RequestParam (name="passwordRepeat") String passwordRepeat,
								ModelMap model, Principal principal){
		String vista="register";
		String mensaje="";
		System.out.println("Nombre: "+usuario.getUsername());
		System.out.println("Email: "+usuario.getEmail());
		System.out.println("Password: "+usuario.getPassword());
		System.out.println("ParwordRepeat: "+passwordRepeat);
		try {
			if(!usuario.getUsername().equals("")) {
				if(!usuario.getEmail().equals("")) {
					if(!usuario.getPassword().equals("")) {
						if(!passwordRepeat.equals("")) {
							if(usuario.getPassword().equals(passwordRepeat)) {
								System.out.println("TODO OK");
								mensaje = this.usuarioService.AddUser(usuario);
							}else {
								mensaje="Contraseña incorrecta";
							}
						}else {
							mensaje="Repetir contraseña.";
						}
					}else {
						mensaje="Contraseña requerido";
					}
				}else {
					mensaje="Email requerido";
				}
			}else {
				mensaje="Nombre de usuario requerido";
			}
			model.addAttribute("mensaje", mensaje);
			this.vistaUtils.setHeader(principal, model);
			return "redirect:/login";
		}catch(Exception e){
			this.vistaUtils.setHeader(principal, model);
			return "/404";
		}
		//return vista;
	}
	
	/**
	 * LLama a la vista de registro de usuario
	 * @param usuario
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String Register(@ModelAttribute ("Usuario") Usuario usuario, ModelMap model, Principal principal) {
		String vista="register";
		model.addAttribute("Usuario", usuario);
		this.vistaUtils.setHeader(principal, model);
		return vista;
	}

}
