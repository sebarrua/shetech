package edu.cpci.shetech.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.repository.UsuarioRepository;



@Transactional
@Service
public class UsuarioService implements _BaseService<Usuario> {
	
    @Autowired
    private UsuarioRepository repositorioUsuario;
	@Autowired
    private EntityManager entityManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RolUsuarioService rolUsuarioService;
	
	
	/**
	 * Agrega un usuario
	 * @param usuario
	 * @return
	 */
	public String AddUser(Usuario usuario) {
		String mensaje="";
		try {
		Usuario newUser = new Usuario();
		newUser.setUsername(usuario.getUsername());
		newUser.setEmail(usuario.getEmail());
		newUser.setActivo(true);
		newUser.setPassword(passwordEncoder.encode(usuario.getPassword()));
		this.repositorioUsuario.save(newUser);
		this.rolUsuarioService.setRolUser(newUser);
		mensaje="Usuario "+usuario.getUsername()+" agregado.";
		return mensaje;
		}catch(Exception e){
			mensaje="Error al agregar usuario.";
			return mensaje;
		}
	}
    
	/**
	 * Recupera el nombre del usuario logueado
	 * @param nombre
	 * @return
	 */
    public Usuario getUsuarioByNombre(String nombre){
        try{
        	String sql = "Select e from " + Usuario.class.getName() + " e"
                    + " where e.username = :nombre";

            Query query = entityManager.createQuery(sql, Usuario.class);

            query.setParameter("nombre",nombre);
            //System.out.println("Nombre de usuario logueado: "+((Usuario) query.getSingleResult()).getUsername());

            return (Usuario) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    
    
    /**
     * Recupera el nombre del usuario logueado
     * @param nombre
     * @return
     */
    public Usuario getUsuarioByNombre1(String nombre){
        try{
            String sql = "Select e from " + Usuario.class.getName() + " e"
                    + " where e.username = :nombre";

            Query query = entityManager.createQuery(sql, Usuario.class);
            Usuario usuario = (Usuario) query.getSingleResult();
           
            query.setParameter("nombre",nombre);
            System.out.println(((Usuario) query.getSingleResult()).getUsername()+" Class: userservice");
            this.entityManager.close();
            return usuario;
           
        }catch (NoResultException e){
            return null;
        }
    }

	@Override
	public List<Usuario> getAll() {
		// TODO Auto-generated method stub
		return this.repositorioUsuario.findAll();
	}

	@Override
	public Usuario getOneById(Long id) {
		// TODO Auto-generated method stub
		return this.repositorioUsuario.getOne(id);
	}

	@Override
	public void save(Usuario entidad) {
		this.repositorioUsuario.save(entidad);
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
}
