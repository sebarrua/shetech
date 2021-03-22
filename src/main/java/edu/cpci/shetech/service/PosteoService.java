package edu.cpci.shetech.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Posteo;
import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.repository.PosteoRepository;

@Service
public class PosteoService implements _BaseService<Posteo> {
	
	@Autowired
	private PosteoRepository posteoRepository;
	
	public String AddPosteo(Posteo posteo, Usuario usuario) {
		String mensaje="";
		Posteo newPosteo = new Posteo();
		newPosteo.setNombre(posteo.getNombre());
		newPosteo.setTexto(posteo.getTexto());
		Date date = new Date();
		newPosteo.setFechaPosteo(date);
		newPosteo.setEmpresa(posteo.getEmpresa());
		newPosteo.setUsuario(usuario);
		this.posteoRepository.save(newPosteo);
		mensaje="Posteo agregado con exito";
		return mensaje;
	}

	@Override
	public List<Posteo> getAll() {
		return this.posteoRepository.findAll();
	}

	@Override
	public Posteo getOneById(Long id) {
		return this.posteoRepository.getOne(id);
	}

	@Override
	public void save(Posteo entidad) {
		this.posteoRepository.save(entidad);
	}

	@Override
	public void delete(Long id) {
		this.posteoRepository.deleteById(id);
	}



}
