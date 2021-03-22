package edu.cpci.shetech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Rol;
import edu.cpci.shetech.entity.RolUsuario;
import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.repository.RolUsuarioRepository;

@Service
public class RolUsuarioService implements _BaseService<RolUsuario> {
	
	@Autowired
	private RolUsuarioRepository rolUsuarioRepository;
	@Autowired
	private RolService rolService;
	
	public void setRolUser(Usuario usuario) {
		RolUsuario newRolUsuario = new RolUsuario();
		Rol rolUsuario = this.rolService.getOneById((long) 2);
		newRolUsuario.setRol(rolUsuario);
		newRolUsuario.setUsuario(usuario);
		this.rolUsuarioRepository.save(newRolUsuario);
	}

	@Override
	public List<RolUsuario> getAll() {
		return this.rolUsuarioRepository.findAll();
	}

	@Override
	public RolUsuario getOneById(Long id) {
		return this.rolUsuarioRepository.getOne(id);
	}

	@Override
	public void save(RolUsuario entidad) {
		this.rolUsuarioRepository.save(entidad);
	}

	@Override
	public void delete(Long id) {
		this.rolUsuarioRepository.deleteById(id);
	}
}
