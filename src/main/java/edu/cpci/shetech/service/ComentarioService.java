package edu.cpci.shetech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.ObjectModel.ComentarioModel;
import edu.cpci.shetech.entity.Comentario;
import edu.cpci.shetech.entity.Posteo;
import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.repository.ComentarioRepository;

@Service
public class ComentarioService implements _BaseService<Comentario> {
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PosteoService posteoService;
	
	public Comentario addComentario(ComentarioModel comentarioModel) {
		Comentario comentario = new Comentario();
		Usuario usuarioComent = this.usuarioService.getUsuarioByNombre(comentarioModel.getUsername());
		Posteo posteoComent = this.posteoService.getOneById(comentarioModel.getPost());
		if(usuarioComent != null && posteoComent != null) {
			comentario.setUsuario(usuarioComent);
			comentario.setPosteo(posteoComent);
			comentario.setTexto(comentarioModel.getTexto());
			this.comentarioRepository.save(comentario);
		}
		return comentario;
	}
	
	
//	public void addComentario(Comentario comentario) {
//				
//	}

	@Override
	public List<Comentario> getAll() {
		return this.comentarioRepository.findAll();
	}

	@Override
	public Comentario getOneById(Long id) {
		return this.comentarioRepository.getOne(id);
	}

	@Override
	public void save(Comentario comentario) {
		this.comentarioRepository.save(comentario);
	}

	@Override
	public void delete(Long id) {
		this.comentarioRepository.deleteById(id);
	}





}
