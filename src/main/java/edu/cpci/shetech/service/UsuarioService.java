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
    private UsuarioRepository usuarioRepository;
	@Autowired
    private EntityManager entityManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RolUsuarioService rolUsuarioService;
	
	public Iterable<Usuario> save(List<Usuario> listUsuarios) {
        return usuarioRepository.saveAll(listUsuarios);
    }
    public Iterable<Usuario> list() {
        return usuarioRepository.findAll();
    }

	
	
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
		this.usuarioRepository.save(newUser);
		if(!newUser.getUsername().equals("admin")) {
			this.rolUsuarioService.setRolUser(newUser);
		}else {
			this.rolUsuarioService.setRolAdmin(newUser);
			this.rolUsuarioService.setRolUser(newUser);
		}
		mensaje="Usuario "+usuario.getUsername()+" agregado.";
		return mensaje;
		}catch(Exception e){
			mensaje="Error al agregar usuario.";
			return mensaje;
		}
	}
	
	public Usuario getusuarioByNombreObject(String nombre) {
		Usuario usuarioReturn=null;
		List<Usuario> listUsuario = this.getAll();
		for(Usuario u: listUsuario) {
			if(u.getUsername().equals(nombre)) {
				usuarioReturn=u;
				break;
			}
		}
		return usuarioReturn;
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
		return this.usuarioRepository.findAll();
	}

	@Override
	public Usuario getOneById(Long id) {
		// TODO Auto-generated method stub
		return this.usuarioRepository.getOne(id);
	}

	@Override
	public void save(Usuario entidad) {
		this.usuarioRepository.save(entidad);
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
}
