package com.letscode.moviebattle.moviebattle.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(unique = true, length = 50)
	private String nome;

	@Override
	public String getAuthority() {
		return this.nome;
	}
	
	public Role(String nome) {
		super();
		this.nome = nome;
	}
	
	public Role() {	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}
