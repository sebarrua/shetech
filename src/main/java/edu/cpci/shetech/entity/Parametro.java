package edu.cpci.shetech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parametro")
public class Parametro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long parametroId;
	
	@Column(name="tipo_parametro")
	private String tipoParametro;
	
	@Column(name="descripcion")
	private String descripcion;
	
	public Parametro() {
		
	}

	public Long getParametroId() {
		return parametroId;
	}

	public void setParametroId(Long parametroId) {
		this.parametroId = parametroId;
	}

	public String getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	

}
