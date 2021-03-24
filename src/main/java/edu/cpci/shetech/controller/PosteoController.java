package edu.cpci.shetech.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cpci.shetech.entity.Empresa;
import edu.cpci.shetech.entity.Posteo;
import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.EmpresaService;
import edu.cpci.shetech.service.PosteoService;
import edu.cpci.shetech.service.UsuarioService;

@Controller
@RequestMapping
public class PosteoController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private PosteoService posteoService;

	
	@GetMapping(value="/posteo")
	public String Posteos(Model model, Principal principal) {
		String vista="";
		return vista;
	}
	
	
	@GetMapping(value="/posteoAdmin")
	public String PosteosAdmin(Model model, Principal principal) {
		String vista="posteosPage";
		List<Posteo> listPosteo = this.posteoService.getAll();
		if(principal!=null) {
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			model.addAttribute("userLog", userLog.getUsername());
		}else {
			model.addAttribute("userNoLog", "noUser");
		}
		model.addAttribute("listPosteo", listPosteo);
		return vista;
	}
	
	@GetMapping(value="/addPosteo")
	public String AddPosteo(@ModelAttribute ("Posteo") Posteo posteo, Model model, Principal principal) {
		String vista="addPost.html";
		List<Empresa> listEmpresa = this.empresaService.getAll();
		model.addAttribute("listaEmpresa", listEmpresa);
		if(principal!=null) {
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			model.addAttribute("userLog", userLog.getUsername());
		}else {
			model.addAttribute("userNoLog", "noUser");
		}
		return vista;
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value="/posteoSave")
	public String PosteoSave(@ModelAttribute ("Posteo") Posteo posteo, Model model, Principal principal) {
		String vista="addPost.html";
		String mensaje="";
		Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
		if(!posteo.getNombre().equals("")) {
			if(!posteo.getTexto().equals("")) {
				if(posteo.getEmpresa()!=null) {
					mensaje=this.posteoService.AddPosteo(posteo, userLog);
				}else {
					mensaje="Empresa requerida";
				}
			}else {
				mensaje="Texto del posteo requerido";
			}
		}else {
			mensaje="Nombre de postero requerido";
		}
		model.addAttribute("mensaje", mensaje);
		if(principal!=null) {
			model.addAttribute("userLog", userLog.getUsername());
		}else {
			model.addAttribute("userNoLog", "noUser");
		}
		return vista;
	}

}
