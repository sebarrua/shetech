package edu.cpci.shetech.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Empresa;
import edu.cpci.shetech.repository.EmpresaRepository;

@Service
public class EmpresaService implements _BaseService<Empresa> {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public List<Empresa> getAll() {
		return this.empresaRepository.findAll();
	}

	@Override
	public Empresa getOneById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Empresa entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
