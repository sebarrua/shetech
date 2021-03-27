package edu.cpci.shetech.ObjectModel;

import javax.persistence.Entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComentarioModel {
	
	private String username;
	private Long post;
	private String texto;
	
	public ComentarioModel() {
		
	}
	
	public ComentarioModel(String username, Long post, String texto) {
		super();
		this.username = username;
		this.post = post;
		this.texto = texto;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getPost() {
		return post;
	}
	public void setPost(Long post) {
		this.post = post;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	

}
