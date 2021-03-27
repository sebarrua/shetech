package edu.cpci.shetech.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="posteo")
public class Posteo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long posteoId;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="descripcion")
	private String descripcion;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parametro", nullable = false)
	private Parametro estado ;
	
	public Posteo() {
		
	}

	public Long getPosteoId() {
		return posteoId;
	}

	public void setPosteoId(Long posteoId) {
		this.posteoId = posteoId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Parametro getEstado() {
		return estado;
	}

	public void setEstado(Parametro estado) {
		this.estado = estado;
	}

	
}
