package edu.cpci.shetech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Parametro;
import edu.cpci.shetech.repository.ParametroRepository;

@Service
public class ParametroService implements _BaseService<Parametro> {
	
	@Autowired
	private ParametroRepository parametroRepository;

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
				
	}

	@Override
	public void delete(Long id) {
	}

}
