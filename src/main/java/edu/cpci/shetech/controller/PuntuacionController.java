package edu.cpci.shetech.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.cpci.shetech.ObjectModel.ComentarioModel;
import edu.cpci.shetech.entity.Posteo;
import edu.cpci.shetech.entity.Puntuacion;
import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.PosteoService;
import edu.cpci.shetech.service.PuntuacionService;
import edu.cpci.shetech.service.UsuarioService;

@Controller
@RequestMapping
public class PuntuacionController {
	
	@Autowired
	private PuntuacionService puntuacionService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PosteoService posteoService;
	@Autowired
	private PosteoController posteoController;
	
	@GetMapping(value="/puntuarMas")
	public ModelAndView PuntuarMas (@ModelAttribute ("ComentarioModel") ComentarioModel comentario,
									@RequestParam(value = "id") Long id, 
									ModelMap model, 
									Principal principal) {
		ModelAndView vista;
		String valor="positivo";
		if(principal!=null) {
			Posteo post = this.posteoService.getOneById(id);
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			Puntuacion puntuacionThisPost = this.puntuacionService.getByPosteoAndUsuario(post, userLog);
			if(puntuacionThisPost==null) {
				this.puntuacionService.addPuntuacion(userLog, post, valor);;
			}else {
				if(!puntuacionThisPost.getValor().equals(valor)) {
					this.puntuacionService.setPuntuacion(userLog, post, valor);
				
				}else {
					valor="sin valorar";
					this.puntuacionService.setPuntuacion(userLog, post, valor);
				}
			}
		}else {
			String unLogue = "Se requiere loguearce para puntuar.";			
			model.addAttribute("mensajePuntuar", unLogue);
		}
		//this.posteoController.Post(id, model, principal);
		vista=this.posteoController.Post(comentario, id, model, principal);
		return vista;
		//return null;
	}
	
	@GetMapping(value="/puntuarMenos")
	public ModelAndView PuntuarMenos (	@ModelAttribute ("ComentarioModel") ComentarioModel comentario,
										@RequestParam(value = "id") Long id, 
										ModelMap model, 
										Principal principal) {
		ModelAndView vista;
		String valor="negativo";
		if(principal!=null) {
			Posteo post = this.posteoService.getOneById(id);
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			Puntuacion puntuacionThisPost = this.puntuacionService.getByPosteoAndUsuario(post, userLog);
			if(puntuacionThisPost==null) {
				this.puntuacionService.addPuntuacion(userLog, post, valor);;
			}else {
				if(!puntuacionThisPost.getValor().equals(valor)) {
					this.puntuacionService.setPuntuacion(userLog, post, valor);
				
				}else {
					valor="sin valorar";
					this.puntuacionService.setPuntuacion(userLog, post, valor);
				}
			}
		}else {
			String unLogue = "Se requiere loguearce para puntuar.";			
			model.addAttribute("mensajePuntuar", unLogue);
		}
		vista=this.posteoController.Post(comentario, id, model, principal);
		return vista;
	}

}
