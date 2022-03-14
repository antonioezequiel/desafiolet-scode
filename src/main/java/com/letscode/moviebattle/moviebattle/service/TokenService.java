package com.letscode.moviebattle.moviebattle.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.letscode.moviebattle.moviebattle.classes.Usuario;
import com.letscode.moviebattle.moviebattle.security.AutenticationTokenFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	@Value("${expiration.value}")
	private String expirecaoToken;
	@Value("${secret.value}")
	private String secret;
	
	public TokenService() {
		super();
	}

	public String gerarToken(Authentication authentication) {
		Date data = new Date();
		Date dataExpiracao = new Date(data.getTime() + Long.parseLong(expirecaoToken));
		Usuario usuario = (Usuario) authentication.getPrincipal();

		return Jwts.builder().setIssuer("jogo de filmes").setSubject(usuario.getNome().toString()).setIssuedAt(data)
				.setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public String getNomeUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return body.getSubject();
	}

	public void invalidarToken(HttpServletRequest request) {
		String token  = AutenticationTokenFilter.recuperarToken(request);
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		body.setExpiration(new Date());
	}

	public String getExpirecaoToken() {
		return expirecaoToken;
	}

	public void setExpirecaoToken(String expirecaoToken) {
		this.expirecaoToken = expirecaoToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
