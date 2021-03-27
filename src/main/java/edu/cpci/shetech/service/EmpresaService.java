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
	
	public Empresa getByNombre(String empresaPost) {
		Empresa empresaReturn = null;
		List<Empresa> listEmpresa = this.getAll();
		for(Empresa e: listEmpresa) {
			if(e.getNombre().equals(empresaPost)) {
				empresaReturn=e;
			}
		}
		return empresaReturn;
	}

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
		this.empresaRepository.save(entidad);
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}



}
