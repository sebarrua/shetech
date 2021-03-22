package edu.cpci.shetech.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="posteo")
public class Posteo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long posteoId;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="fecha_posteo")
	private Date fechaPosteo;
	
	@Column(name="texto")
	private String texto;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa ;
	
	public Posteo() {
		
	}

	public Long getPosteoId() {
		return posteoId;
	}

	public void setPosteoId(Long posteoId) {
		this.posteoId = posteoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaPosteo() {
		return fechaPosteo;
	}

	public void setFechaPosteo(Date fechaPosteo) {
		this.fechaPosteo = fechaPosteo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
	
	
	

}
