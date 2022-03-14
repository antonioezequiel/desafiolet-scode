package com.letscode.moviebattle.moviebattle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.moviebattle.moviebattle.service.MovieService;
import com.letscode.moviebattle.moviebattle.service.UsuarioService;

@RestController
public class MovieController {
	MovieService movieService;
	UsuarioService usuarioService;
	
	@Autowired
	public MovieController(MovieService movieService, UsuarioService usuarioService) {
		super();
		this.movieService = movieService;
		this.usuarioService = usuarioService;
	}

	@GetMapping("/carregar")
	public String carregarDados() {
		movieService.carregarFilmesIMDB();
		usuarioService.criarUsuariosPadrao();
		return "dados carregados com sucesso";
	}
	
	
}
