package com.letscode.moviebattle.moviebattle.classes;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class LoginForm {
	private String login;
	private String senha;
	
	public UsernamePasswordAuthenticationToken converterDados() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}
}
