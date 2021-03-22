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
import org.springframework.web.bind.annotation.ResponseBody;

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
	//@ResponseBody
	public String Posteos(Model model, Principal principal) {
		if(principal!=null) {
			System.out.println(principal.getName());
		}
		String vista="posteosPage";
		if(principal!=null) {
			System.out.println("USUARIO LOGUEADO: "+principal.getName());
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			model.addAttribute("userLog", userLog.getUsername());
		}else {
			System.out.println("NO HAY PRINCIPAL");
			model.addAttribute("userNoLog", "noUser");
		}
		return vista;
	}
	
	@GetMapping(value="/addPosteo")
	public String AddPosteo(@ModelAttribute ("Posteo") Posteo posteo, Model model, Principal principal) {
		String vista="addPost.html";
		List<Empresa> listEmpresa = this.empresaService.getAll();
		model.addAttribute("listaEmpresa", listEmpresa);
		if(principal!=null) {
			System.out.println("USUARIO LOGUEADO: "+principal.getName());
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			model.addAttribute("userLog", userLog.getUsername());
		}else {
			System.out.println("NO HAY PRINCIPAL");
			model.addAttribute("userNoLog", "noUser");
		}
		return vista;
	}
	
	@PostMapping(value="/posteoSave")
	public String PosteoSave(@ModelAttribute ("Posteo") Posteo posteo, Model model, Principal principal) {
		String vista="addPost.html";
		String mensaje="";
		Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
		System.out.println(posteo.getNombre());
		System.out.println(posteo.getTexto());
		System.out.println(posteo.getEmpresa());
		if(!posteo.getNombre().equals("")) {
			System.out.println("1");
			if(!posteo.getTexto().equals("")) {
				System.out.println("2");
				if(posteo.getEmpresa()!=null) {
					System.out.println("3");
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
		return vista;
	}

}
