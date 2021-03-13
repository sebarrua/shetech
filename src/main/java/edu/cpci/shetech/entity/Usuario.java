package edu.cpci.shetech.entity;

import javax.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long usuarioId;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="is_activo")
	private boolean isActivo;
	
	public Usuario() {
		
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}
	
	
	

}
