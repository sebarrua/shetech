package edu.cpci.shetech.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Parametro;
import edu.cpci.shetech.repository.ParametroRepository;

@Service
@Transactional
public class ParametroService implements _BaseService<Parametro> {
	
	@Autowired
	private ParametroRepository parametroRepository;
	
	public Parametro getEstadoPostByDescripcion(String parametroDescrip) {
		Parametro parametroReturn = null;
		List<Parametro> parametrosEstadoPosteo = this.getParametrosEstadoPosteo();
		for(Parametro p: parametrosEstadoPosteo) {
			if(p.getDescripcion().equals(parametroDescrip)) {
				parametroReturn=p;
				break;
			}
		}
		
		return parametroReturn;
	}
	
	public List<Parametro> getParametrosEstadoPosteo() {
		List<Parametro> listParametroEstadosPosteo = this.parametroRepository.findByTipoParametro("ESTADO.POSTEO");
		return listParametroEstadosPosteo;
	}

	@Override
	public List<Parametro> getAll() {
		return this.parametroRepository.findAll();
	}

	@Override
	public Parametro getOneById(Long id) {
		return this.parametroRepository.getOne(id);
	}

	@Override
	public void save(Parametro entidad) {
			this.parametroRepository.save(entidad);
	}

	@Override
	public void delete(Long id) {
	}
}
