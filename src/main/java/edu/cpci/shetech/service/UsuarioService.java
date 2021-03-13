package edu.cpci.shetech.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.repository.UsuarioRepository;



@Service
public class UsuarioService implements _BaseService<Usuario> {
	
	
	@Autowired
    private EntityManager entityManager;
    
    public Usuario getUsuarioByNombre(String nombre){
        try{
        	String sql = "Select e from " + Usuario.class.getName() + " e"
                    + " where e.nombre = :nombre and e.id = 1";

            Query query = entityManager.createQuery(sql, Usuario.class);

            query.setParameter("nombre",nombre);
            System.out.println("Nombre de usuario logueado: "+((Usuario) query.getSingleResult()).getNombre());

            return (Usuario) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }


    
    //Recupera el nombre del usuario logueado
    public Usuario getUsuarioByNombre1(String nombre){
        try{
            String sql = "Select e from " + Usuario.class.getName() + " e"
                    + " where e.nombre = :nombre and e.id = 1";

            Query query = entityManager.createQuery(sql, Usuario.class);
            Usuario usuario = (Usuario) query.getSingleResult();
           
            query.setParameter("nombre",nombre);
            System.out.println(((Usuario) query.getSingleResult()).getNombre()+" Class: userservice");
            this.entityManager.close();
            return usuario;
           
        }catch (NoResultException e){
            return null;
        }
    }
    @Autowired
    private UsuarioRepository repositorioUsuario;


	@Override
	public List<Usuario> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getOneById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void save(Usuario entidad) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
