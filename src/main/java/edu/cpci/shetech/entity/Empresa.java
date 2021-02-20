package edu.cpci.shetech.entity;

import javax.persistence.*;

@Entity
public class Empresa {
	
	@Id
	@Column(name="id")
	private Long empresaId;
	
	@Column(name="nombre")
	private String nombre;
	
	public Empresa() {
		
	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
