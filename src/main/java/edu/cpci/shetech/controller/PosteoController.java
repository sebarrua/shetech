package edu.cpci.shetech.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.cpci.shetech.ObjectModel.ComentarioModel;
import edu.cpci.shetech.entity.Comentario;
import edu.cpci.shetech.entity.Empresa;
import edu.cpci.shetech.entity.Parametro;
import edu.cpci.shetech.entity.Posteo;
import edu.cpci.shetech.entity.Puntuacion;
import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.EmpresaService;
import edu.cpci.shetech.service.ParametroService;
import edu.cpci.shetech.service.PosteoService;
import edu.cpci.shetech.service.PuntuacionService;
import edu.cpci.shetech.service.UsuarioService;
import edu.cpci.shetech.utils.VistaUtils;

@Controller
@RequestMapping
public class PosteoController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private PosteoService posteoService;
	@Autowired
	private VistaUtils vistaUtils;
	@Autowired
	private ParametroService parametroService;
	@Autowired
	private PuntuacionService puntuacionService;

	
	@GetMapping(value="/posteos")
	public String Posteos(ModelMap model, Principal principal) {
		String vista="posts";
		List<Posteo> listPosteo = this.posteoService.getPosteosAprobados();
		model.addAttribute("listPosteo", listPosteo);
		this.vistaUtils.setHeader(principal, model);
		return vista;
	}
	
	@GetMapping(value="/post")
	public ModelAndView Post(	@ModelAttribute ("ComentarioModel") ComentarioModel comentarioModel, 
								@RequestParam(value = "id") Long id, ModelMap model, Principal principal) {
		String vista="post";
		Posteo post = this.posteoService.getOneById(id);
		model.addAttribute("post", post);
		model.addAttribute("ComentarioModel", comentarioModel);
		if(principal!=null) {
			Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
			Puntuacion puntuacionThisPostAndUserLog = this.puntuacionService.getByPosteoAndUsuario(post, userLog);
			if(puntuacionThisPostAndUserLog!=null) {
				model.addAttribute("puntuacion", puntuacionThisPostAndUserLog.getValor());
			}else {
				String sinPuntuar="Sin valorar";
				model.addAttribute("puntuacion", sinPuntuar);
			}
		}else {
			String sinPuntuar="Sin valorar";
			model.addAttribute("puntuacion", sinPuntuar);
		}
		Long puntuacionTotalThisPost = this.puntuacionService.getPuntosTotalByPost(post);
		model.addAttribute("puntosPost", puntuacionTotalThisPost);
		comentarioModel.setTexto("");
		this.vistaUtils.setHeader(principal, model);
		return new ModelAndView(vista,  model);
		//return vista;
	}
	
	@PostMapping(value="/editPosteoSave")
	public String EditPosteoSave(@ModelAttribute ("Posteo") Posteo posteo, Model model, Principal principal) {
		System.out.println("POSTEO CONTROLLER: editPosteoSave");
		Posteo posteoSelect=this.posteoService.editSave(posteo);
		String vista ="redirect:/editarPosteo/"+posteo.getPosteoId();
		return vista;
	}
	
	@GetMapping(value="/editarPosteo/{posteoId}")
	public String EditarPosteo(@PathVariable ("posteoId") Long posteoId, ModelMap model, Principal principal) {
		String vista="editPost";
		System.out.println("VISTA POSTEO CONTROLLER");
		Posteo posteoSelect=this.posteoService.getOneById(posteoId);
		List<Parametro> listParametroEstadosPosteo=this.parametroService.getParametrosEstadoPosteo();
		List<Empresa> listEmpresa =this.empresaService.getAll();
		model.addAttribute("listaEstado", listParametroEstadosPosteo);
		model.addAttribute("Posteo", posteoSelect);
		model.addAttribute("listaEmpresa", listEmpresa);
		this.vistaUtils.setHeader(principal, model);
		return vista;
	}
	
	@GetMapping(value="/bloquearPosteo/{posteoId}")
	public String bloquearPosteo(@PathVariable ("posteoId") Long posteoId, Model model, Principal principal) {
		String vista="redirect:/posteoAdmin";
		System.out.println("BLOQUEAR POSTEO CONTROLLER");
		this.posteoService.bloquearPosteo(posteoId);
		return vista;
	}
	
	@GetMapping(value="/aprobarPosteo/{posteoId}")
	public String aprobarPosteo(@PathVariable ("posteoId") Long posteoId, Model model, Principal principal) {
		String vista="redirect:/posteoAdmin";
		System.out.println("APROBAR POSTEO CONTROLLER");
		this.posteoService.aprobarPosteo(posteoId);
		return vista;
	}
	
	@GetMapping(value="/deletePosteo/{posteoId}")
	public String DeletePosteo(@PathVariable ("posteoId") Long posteoId, Model model, Principal principal) {
		String vista="redirect:/posteoAdmin";
		System.out.println("BORRAR POSTEO CONTROLLER "+ posteoId);
		this.posteoService.delete(posteoId);
		return vista;
	}
	
	
	@GetMapping(value="/posteoAdmin")
	public String PosteosAdmin(ModelMap model, Principal principal) {
		String vista="posteosPageAdmin";
		//Usuario usuario = this.usuarioService.getOneById((long) 3);
		
		/*======TODOS LOS POSTEOS========*/
		List<Posteo> listPosteo = this.posteoService.getAll();
//		/*======POSTEOS APROBADOS========*/
//		List<Posteo> listPosteoAprobados = this.posteoService.getPosteosAprobados();
//		/*======POSTEOS POR USUARIO========*/
//		List<Posteo> listPosteoByUsuario = this.posteoService.getPosteosByUsuario(usuario);
//		/*======POSTEOS POR USUARIO Y APROBADOS========*/
//		List<Posteo> listPosteoByUsuarioAprobados = this.posteoService.getPosteosByUsuarioAprobados(usuario);
		
		model.addAttribute("listPosteo", listPosteo);
		this.vistaUtils.setHeader(principal, model);
		return vista;
	}
	
	@GetMapping(value="/addPosteo")
	public String AddPosteo(@ModelAttribute ("Posteo") Posteo posteo, ModelMap model, Principal principal) {
		String vista="addPost";
		List<Empresa> listEmpresa = this.empresaService.getAll();
		model.addAttribute("Posteo", posteo);
		model.addAttribute("listaEmpresa", listEmpresa);
		String addEmpresa="/addEmpresa";
		model.addAttribute("addEmpresa", addEmpresa);
		this.vistaUtils.setHeader(principal, model);
		return vista;
	}
	
	@PostMapping(value="/posteoSave")
	public String PosteoSave(@ModelAttribute ("Posteo") Posteo posteo, Model model, Principal principal) {
		String vista="redirect:/posteos";
		String mensaje="";
		Usuario userLog = this.usuarioService.getUsuarioByNombre(principal.getName());
		if(!posteo.getTitulo().equals("")) {
			if(!posteo.getDescripcion().equals("")) {
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
		return vista;
	}

}
