package edu.cpci.shetech.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Rol;
import edu.cpci.shetech.entity.RolUsuario;


@Service
public class RolService implements _BaseService<Rol> {
    @Autowired
    private EntityManager entityManager;
    
    @Override
	public List<Rol> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Rol> getRolesByUser(Long userId) {
		try {
	   		TypedQuery<Rol> query = entityManager.createQuery("SELECT r FROM "+RolUsuario.class.getName()+" ru INNER JOIN ru.rol r WHERE ru.usuario.id = :userId", Rol.class);
	   		query.setParameter("userId", userId);
	   		List<Rol> rolList = query.getResultList();
	   		return rolList;
	   	}catch(NoResultException e){
            return null;	   		
	   	}
	}


	@Override
	public Rol getOneById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(Rol entidad) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
