package edu.cpci.shetech.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import edu.cpci.shetech.ObjectModel.ComentarioModel;
import edu.cpci.shetech.service.ComentarioService;

@Controller
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;
	@Autowired
	private PosteoController posteoController;
	

	@GetMapping(value="/addComentario")
	public ModelAndView AddComentario (	@ModelAttribute ("ComentarioModel") ComentarioModel comentarioModel,
										ModelMap model, 
										Principal principal) {
		ModelAndView vista;
		System.out.println("ComentarioController addComentario");
		System.out.println("Usuario: "+comentarioModel.getUsername());
		System.out.println("Post: "+comentarioModel.getPost());
		System.out.println("Texto: "+comentarioModel.getTexto());
		if(principal!=null) {
			if(!comentarioModel.getTexto().equals("")) {
				this.comentarioService.addComentario(comentarioModel);
			}else {
				String unComent ="Ingrese comentario....";
				model.addAttribute("mensajeComentar", unComent);
			}
		}else {
			String unLogue ="¡Lo sentimos! Requiere loguearce para comentar.";
			model.addAttribute("mensajeComentar", unLogue);
		}
		vista=this.posteoController.Post(comentarioModel, comentarioModel.getPost(), model, principal);
		return vista;
		//return modelAndView;
	}
}
