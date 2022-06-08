package com.letscode.moviebattle.moviebattle.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.letscode.moviebattle.moviebattle.classes.Movie;
import com.letscode.moviebattle.moviebattle.classes.UserQuiz;
import com.letscode.moviebattle.moviebattle.classes.UserQuizAnswer;
import com.letscode.moviebattle.moviebattle.classes.DTO.MovieRankingDTO;
import com.letscode.moviebattle.moviebattle.classes.DTO.MovieSortearDTO;
import com.letscode.moviebattle.moviebattle.repository.UserQuizRepository;

class QuizServiceTest {
	@Mock
	UserQuizRepository userQuizRepositoryMock;
	@Mock
	MovieService movieServiceMock;
	@Mock
	TokenService tokenServiceMock;
	QuizService quizService;
	@Mock
	UserQuiz userQuiz = null;
	boolean jogadaAtiva = false;
	List<MovieSortearDTO> moviesDTO;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		gerarListMoviessDTO();
		this.userQuiz = usuarioSave();
		this.quizService = new QuizService(userQuizRepositoryMock, movieServiceMock, tokenServiceMock);
		this.quizService.setUserQuiz(userQuiz);
		this.quizService.setMovies(moviesDTO);
	}

	@Test
	void testVerifyAnswer() {
		List<Movie> moviesList = gerarListMovies();
		Mockito.when(movieServiceMock.geraListMovies(moviesDTO)).thenReturn(moviesList);
		this.userQuiz = quizService.verifyAnswer(gerarUserQuizAnswer());
		assertEquals(10.5, userQuiz.getScore());
		assertEquals(2, userQuiz.getLife());
	}
	
	@Test
	void testSavePart() {
		Mockito.when(userQuizRepositoryMock.save(userQuiz)).thenReturn(userQuiz);
		var retorno = quizService.savePart();
		assertTrue(retorno);
	}
	
	@Test
	void testGetRanking() {
		List<UserQuiz> usersRanking = gerarListUserQuiz();
		Mockito.when(userQuizRepositoryMock.findAllByOrderByScoreDesc()).thenReturn(usersRanking);
		List<MovieRankingDTO> usersRankingDTO = quizService.getRanking(1);
		MovieRankingDTO movieranking = usersRankingDTO.get(0);
		assertEquals("jose", movieranking.getUserQuiz());
	}

	private List<Movie> gerarListMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = new Movie("16", "Robin hood","2015", 1652.2, "158995");
		Movie movie2 = new Movie("10", "Casa de papel","2020", 78554.0, "32525");
		movies.add(movie2);
		movies.add(movie);
		return movies;
	}
	
	private List<UserQuiz> gerarListUserQuiz() {
		List<UserQuiz> userQuizList = new ArrayList<UserQuiz>();
		UserQuiz serQuiz = new UserQuiz(50, "jose",42, null, null, 0);
		UserQuiz serQuiz2 = new UserQuiz(35, "maria",12, null, null, 0);
		userQuizList.add(serQuiz);
		userQuizList.add(serQuiz2);
		return userQuizList;
	}

	private void gerarListMoviessDTO() {
		this.moviesDTO = new ArrayList<MovieSortearDTO>();
		MovieSortearDTO dto = new MovieSortearDTO("Robin hood", "2015", "10");
		MovieSortearDTO dto2 = new MovieSortearDTO("Casa de papel", "2020", "16");
		moviesDTO.add(dto2);
		moviesDTO.add(dto);
	}
	
	private UserQuizAnswer gerarUserQuizAnswer() {	
		UserQuizAnswer userQuizAnswer = new UserQuizAnswer();
		userQuizAnswer.setImdbID("16");
		userQuizAnswer.setLoginUser("antonio");		
		return userQuizAnswer;
	}
	
	private UserQuiz usuarioSave() {
		UserQuiz userQuiz = new UserQuiz();
		userQuiz.setScore(10);
		userQuiz.setId(10);
		userQuiz.setUserQuiz("jose");
		userQuiz.setLife(3);
		userQuiz.setRound(15);;
		return userQuiz;
	}
}
