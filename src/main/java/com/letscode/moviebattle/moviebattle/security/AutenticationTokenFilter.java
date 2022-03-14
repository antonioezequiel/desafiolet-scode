package com.letscode.moviebattle.moviebattle.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.letscode.moviebattle.moviebattle.classes.Usuario;
import com.letscode.moviebattle.moviebattle.service.TokenService;
import com.letscode.moviebattle.moviebattle.service.UsuarioService;

public class AutenticationTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UsuarioService usuarioService;

	public AutenticationTokenFilter(TokenService tokenService, UsuarioService usuarioService) {
		super();
		this.tokenService = tokenService;
		this.usuarioService = usuarioService;
	}

	public AutenticationTokenFilter() {}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean tokenvalido = tokenService.isValid(token);
		if(tokenvalido) {
			realizarAutenticacao(token);
		}
		filterChain.doFilter(request, response);
	}

	private void realizarAutenticacao(String token) {
		String nomeUsuario = tokenService.getNomeUsuario(token);
		Usuario usuario = usuarioService.loadUserByUsername(nomeUsuario);
		UsernamePasswordAuthenticationToken authentication =  new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);		
	}

	public static String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer"))
			return null;
		else
			return token.substring(7, token.length());
	}

}
