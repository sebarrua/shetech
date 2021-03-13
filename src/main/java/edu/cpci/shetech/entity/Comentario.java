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
@Table(name="comentario")
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long comentarioId;
	
	@Column(name="texto")
	private String texto;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_posteo", nullable = false)
	private Posteo posteo;

}
