package com.letscode.moviebattle.moviebattle.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.moviebattle.moviebattle.classes.Movie;
import com.letscode.moviebattle.moviebattle.classes.UserQuiz;
import com.letscode.moviebattle.moviebattle.classes.UserQuizAnswer;
import com.letscode.moviebattle.moviebattle.classes.DTO.MovieRankingDTO;
import com.letscode.moviebattle.moviebattle.classes.DTO.MovieSortearDTO;
import com.letscode.moviebattle.moviebattle.repository.UserQuizRepository;
import com.letscode.moviebattle.moviebattle.security.AutenticationTokenFilter;

@Service
public class QuizService {

	UserQuizRepository userQuizRepository;
	MovieService movieService;
	TokenService tokenService;
	UserQuiz userQuiz = null;
	boolean jogadaAtiva = false;
	List<MovieSortearDTO> moviesDTO;
	
	@Autowired
	public QuizService(UserQuizRepository userQuizRepository, MovieService movieService, TokenService tokenService) {
		super();
		this.userQuizRepository = userQuizRepository;
		this.movieService = movieService;
		this.tokenService = tokenService;
	}

	public UserQuiz verifyAnswer(UserQuizAnswer quizUserAnswer) {
		List<Movie> movies = movieService.geraListMovies(moviesDTO);

		if (movies.get(0).getImdbId().equals(quizUserAnswer.getImdbID())) {
			userQuiz.setScore(userQuiz.getScore() + 1);
			userQuiz.setMessage("Seu score atual é: " + userQuiz.getScore());
		} else {
			userQuiz.setLife(userQuiz.getLife() - 1);
			userQuiz.setMessage("você errou! Ainda resta " + userQuiz.getLife() + " vidas");
		}
		userQuiz.setRound(userQuiz.getRound() + 1);
		jogadaAtiva = false;
		return userQuiz;
	}

	public boolean savePart() {
		try {
			calculaAcerto();
			userQuizRepository.save(this.userQuiz);
			reiniciaDadosJogador();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void reiniciaDadosJogador() {
		jogadaAtiva = false;
		userQuiz = null;
		moviesDTO = null;
	}

	private void calculaAcerto() {
		var partidas = this.userQuiz.getRound();
		var acertos = this.userQuiz.getScore();
		this.userQuiz.setScore((acertos / partidas) * partidas);
	}

	public List<MovieRankingDTO> getRanking() {
		List<UserQuiz> usersRanking = userQuizRepository.findAllByOrderByScoreDesc();
		List<MovieRankingDTO> usersRankingDTO = new ArrayList<MovieRankingDTO>();
		for (UserQuiz user : usersRanking) {
			usersRankingDTO.add(user.obterUsuarioDTO());
		}
		return usersRankingDTO;
	}

	public UserQuiz configurarDadosPartida(HttpServletRequest request) {
		userQuiz = new UserQuiz();
		moviesDTO = new ArrayList<MovieSortearDTO>();
		String token = AutenticationTokenFilter.recuperarToken(request);
		userQuiz.setUserQuiz(tokenService.getNomeUsuario(token));
		userQuiz.setScore(0);
		userQuiz.setLife(3);
		userQuiz.setRound(0);
		userQuiz.setMessage("Jogo Iniciado, sortei as Cartas!");
		return userQuiz;
	}

	public boolean usuarioLogado() {
		if (userQuiz != null)
			return true;
		else
			return false;
	}

	public boolean isJogadaAtiva() {
		return jogadaAtiva;
	}

	public void setJogadaAtiva(boolean jogadaAtiva) {
		this.jogadaAtiva = jogadaAtiva;
	}

	public UserQuiz getUserQuiz() {
		return userQuiz;
	}

	public void setUserQuiz(UserQuiz userQuiz) {
		this.userQuiz = userQuiz;
	}

	public boolean existeVidas() {
		if (userQuiz.getLife() > 0)
			return true;
		else
			return false;
	}

	public List<MovieSortearDTO> getMovies() {
		return moviesDTO;
	}

	public void setMovies(List<MovieSortearDTO> movies) {
		this.moviesDTO = movies;
	}
}
