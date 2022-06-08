package com.letscode.moviebattle.moviebattle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.moviebattle.moviebattle.classes.UserQuiz;
import com.letscode.moviebattle.moviebattle.classes.UserQuizAnswer;
import com.letscode.moviebattle.moviebattle.classes.DTO.MovieSortearDTO;
import com.letscode.moviebattle.moviebattle.service.MovieService;
import com.letscode.moviebattle.moviebattle.service.QuizService;

@RestController
@RequestMapping("/api/movieshow")
public class PlayController {
	@Autowired
	MovieService movieService;
	@Autowired
	QuizService quizService;

	@GetMapping("/iniciar")
	public ResponseEntity<?> iniciar(HttpServletRequest request) {
		if (!quizService.usuarioLogado()) {
			UserQuiz userQuiz = quizService.configurarDadosPartida(request);
			return ResponseEntity.ok(userQuiz);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/sortear")
	public ResponseEntity<?> sortear() {
		if (quizService.usuarioLogado()) {
			if (!quizService.isJogadaAtiva()) {
				try {
					List<MovieSortearDTO> movies = movieService.sortearDoisFilmes();
					return ResponseEntity.ok(movies);
				} catch (Exception e) {
					return ResponseEntity.badRequest().build();
				}
			} else {
				return ResponseEntity.ok(quizService.getMovies());
			}
		}
		return ResponseEntity.ok("É necessário iniciar um novo jogo");
	}

	@PostMapping("/jogar")
	public ResponseEntity<?> jogar(@RequestBody UserQuizAnswer quizUserAnswer) {
		UserQuiz userQuiz = null;
		if (quizService.usuarioLogado()) {
			if (quizService.isJogadaAtiva()) {
				userQuiz = quizService.verifyAnswer(quizUserAnswer);
				if (!quizService.existeVidas()) 
					userQuiz.setMessage(finalizarPorVidas());				
			} else {
				userQuiz = quizService.getUserQuiz();
				userQuiz.setMessage("É necessário realizar o sorteio dos filmes");
			}
		} else {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(userQuiz);
	}

	@GetMapping("/finalizar")
	public ResponseEntity<?> finalizar() {
		
		UserQuiz userQuiz = null;
		if (quizService.usuarioLogado()) {
			userQuiz = quizService.getUserQuiz();
			quizService.savePart();			
			userQuiz.setMessage("O jogo acabou. Dados da partida salvos com sucesso");
			return ResponseEntity.ok(userQuiz);
		}
		return ResponseEntity.noContent().build();
	}
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/ranking")
	public ResponseEntity<?> ranking(@RequestParam String page) {
		
		try {
			return ResponseEntity.ok(quizService.getRanking(Integer.parseInt(page)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	private String finalizarPorVidas() {
		quizService.savePart();
		return "O jogo acabou. Você não tem mais vidas. Dados da partida salvos com sucesso";
	}
}
