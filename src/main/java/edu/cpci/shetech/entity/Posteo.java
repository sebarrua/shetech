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
	
	

}
