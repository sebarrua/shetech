package edu.cpci.shetech.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import edu.cpci.shetech.entity.Rol;
import edu.cpci.shetech.entity.RolUsuario;
import edu.cpci.shetech.repository.RolRepository;
import edu.cpci.shetech.repository.RolUsuarioRepository;


@Transactional
@Service
public class RolService implements _BaseService<Rol> {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RolRepository rolRepository;
    
    @Override
	public List<Rol> getAll() {
		// TODO Auto-generated method stub
		return this.rolRepository.findAll();
	}
    public List<String> getNombreRoles(Long userId) {
	    String sql = "Select ur.rol.nombre from " + RolUsuario.class.getName() + " ur " //
	            + " where ur.usuario.id = :userId ";
	
	    Query query = this.entityManager.createQuery(sql, String.class);
	    query.setParameter("userId", userId);
	    //System.out.println(query.getResultList()+" class: RolService");
	    return query.getResultList();
	    }


	public List<Rol> getRolesByUser(Long userId) {
		try {
	   		TypedQuery<Rol> query = entityManager.createQuery("SELECT r FROM "+RolUsuario.class.getName()+" ru INNER JOIN ru.rol r WHERE ru.usuario.usuarioId = :userId", Rol.class);
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
		return this.rolRepository.getOne(id);
	}


	@Override
	public void save(Rol entidad) {
		this.rolRepository.save(entidad);
		
	}


	@Override
	public void delete(Long id) {
		this.rolRepository.deleteById(id);
		
	}

}
