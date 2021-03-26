package edu.cpci.shetech.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Parametro;
import edu.cpci.shetech.entity.Posteo;
import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.repository.PosteoRepository;

@Service
@Transactional
public class PosteoService implements _BaseService<Posteo> {
	
	@Autowired
	private PosteoRepository posteoRepository;
	@Autowired
	private ParametroService parametroService;
	
	public Posteo editSave(Posteo posteo) {
		Posteo posteoSelect = this.getOneById(posteo.getPosteoId());
		posteoSelect.setTitulo(posteo.getTitulo());
		posteoSelect.setDescripcion(posteo.getDescripcion());
		posteoSelect.setTexto(posteo.getTexto());
		posteoSelect.setEstado(posteo.getEstado());
		posteoSelect.setEmpresa(posteo.getEmpresa());
		return posteoSelect;
	}
	
	public List<Posteo> getPosteosByUsuarioAprobados(Usuario usuario){
		Parametro parametroPostAprobado = this.parametroService.getOneById((long) 2);
		List<Posteo> listPosteosByUsuarioAprobados = this.posteoRepository.findByUsuarioAndEstado(usuario, parametroPostAprobado);
		return listPosteosByUsuarioAprobados;
	}
	
	public List<Posteo> getPosteosAprobados(){
		Parametro parametroPostAprobado = this.parametroService.getOneById((long) 2);
		List<Posteo> listPosteosAprobados = this.posteoRepository.getPosteosByEstado(parametroPostAprobado);
		return listPosteosAprobados;
	}
	
	
	public List<Posteo> getPosteosByUsuario(Usuario usuario){
		List<Posteo> listPosteosByUsuario = this.posteoRepository.getPosteosByUsuario(usuario);
		return listPosteosByUsuario;
	}
	
	public void bloquearPosteo(Long posteoId) {
		Posteo posteoSelect = this.posteoRepository.getOne(posteoId);
		Parametro posteoBloqueado = this.parametroService.getOneById((long) 3);
		posteoSelect.setEstado(posteoBloqueado);	
		this.posteoRepository.save(posteoSelect);
	}
	
	public void aprobarPosteo(Long posteoId) {
		Posteo posteoSelect = this.posteoRepository.getOne(posteoId);
		Parametro posteoAprobado = this.parametroService.getOneById((long) 2);
		posteoSelect.setEstado(posteoAprobado);	
		this.posteoRepository.save(posteoSelect);
	}
	
	public String AddPosteo(Posteo posteo, Usuario usuario) {
		String mensaje="";
		Posteo newPosteo = new Posteo();
		newPosteo.setTitulo(posteo.getTitulo());
		newPosteo.setDescripcion(posteo.getDescripcion());
		newPosteo.setTexto(posteo.getTexto());
		Date date = new Date();
		newPosteo.setFechaPosteo(date);
		newPosteo.setEmpresa(posteo.getEmpresa());
		newPosteo.setUsuario(usuario);
		Parametro posteoEnRevision = this.parametroService.getOneById((long) 1);
		newPosteo.setEstado(posteoEnRevision);
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
