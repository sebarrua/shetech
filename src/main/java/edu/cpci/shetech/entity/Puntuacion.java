package edu.cpci.shetech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="puntuacion")
public class Puntuacion {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long puntuacionId;
	
	@Column(name="valor")
	private String valor;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_posteo", nullable = false)
	private Posteo posteo;
	
	public Puntuacion() {
		
	}

	public Long getPuntuacionId() {
		return puntuacionId;
	}

	public void setPuntuacionId(Long puntuacionId) {
		this.puntuacionId = puntuacionId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Posteo getPosteo() {
		return posteo;
	}

	public void setPosteo(Posteo posteo) {
		this.posteo = posteo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
