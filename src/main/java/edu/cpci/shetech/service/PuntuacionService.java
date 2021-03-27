package edu.cpci.shetech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Posteo;
import edu.cpci.shetech.entity.Puntuacion;
import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.repository.PuntuacionRepository;

@Service
public class PuntuacionService implements _BaseService<Puntuacion>{
	
	@Autowired
	private PuntuacionRepository puntuacionRepository;

	public Long getPuntosTotalByPost(Posteo post) {
		List<Puntuacion> listPuntuacionByPost = this.puntuacionRepository.getPuntuacionByPosteo(post);
		Long puntuacionFinal=(long)0;
		if(listPuntuacionByPost!=null) {
			for(Puntuacion p: listPuntuacionByPost) {
				if(p.getValor().equals("Positivo")) {
					puntuacionFinal=puntuacionFinal+1;
				}
				if(p.getValor().equals("Negativo")) {
					puntuacionFinal=puntuacionFinal-1;
				}
			}
		}
		return puntuacionFinal;
	}
	public void setPuntuacion(Usuario usuario, Posteo post, String valor) {
		Puntuacion puntuacionByPosteo=(Puntuacion) this.puntuacionRepository.findByPosteoAndUsuario(post, usuario);
		puntuacionByPosteo.setValor(valor);
	}
	
	public void addPuntuacion(Usuario usuario, Posteo post, String valor) {
		Puntuacion addPuntuacion = new Puntuacion();
		addPuntuacion.setUsuario(usuario);
		addPuntuacion.setPosteo(post);
		addPuntuacion.setValor(valor);
		this.puntuacionRepository.save(addPuntuacion);
	}
	
	public Puntuacion getByPosteoAndUsuario(Posteo post, Usuario usuario) {
		Puntuacion puntuacionByPosteo=(Puntuacion) this.puntuacionRepository.findByPosteoAndUsuario(post, usuario);
		System.out.println("PuntuacionService getByPosteoAndUsuario "+ puntuacionByPosteo);
		return puntuacionByPosteo;
	}

	@Override
	public List<Puntuacion> getAll() {
		return this.puntuacionRepository.findAll();
	}

	@Override
	public Puntuacion getOneById(Long id) {
		return this.puntuacionRepository.getOne(id);
	}

	@Override
	public void save(Puntuacion entidad) {
		this.puntuacionRepository.save(entidad);
	}

	@Override
	public void delete(Long id) {
		this.puntuacionRepository.deleteById(id);
	}
}
