package com.letscode.moviebattle.moviebattle.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.letscode.moviebattle.moviebattle.classes.Movie;
import com.letscode.moviebattle.moviebattle.classes.DTO.MovieSortearDTO;
import com.letscode.moviebattle.moviebattle.repository.MovieRepository;
@SpringBootTest
class MovieServiceTest {
	
	@Mock
	@Autowired
	MovieRepository movieRepository;
	@Mock
	@Autowired
	QuizService quizService;
	@Autowired
	MovieService movieService;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void beforeEach() throws IOException {
		MockitoAnnotations.initMocks(this);		
		//this.movieService = new MovieService(movieRepository, quizService);
	}
	
	@Test
	void testSortearDoisFilmes() {
		List<Movie> moviesList = gerarListMovies();
		Mockito.when(movieRepository.findAll()).thenReturn(moviesList);
		List<MovieSortearDTO> moviesList2 = movieService.sortearDoisFilmes();		
		assertNotEquals(moviesList2.get(0), moviesList2.get(1));
	}
	
	private List<Movie> gerarListMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = new Movie("16", "Robin hood","2015", 1652.2, "158995");
		Movie movie2 = new Movie("10", "Casa de papel","2020", 78554.0, "32525");
		Movie movie3 = new Movie("26", "Em nome do amor","2015", 8954.0, "525");
		Movie movie4 = new Movie("96", "Casa de vidro","1998", 95554.0, "33625");
		Movie movie5 = new Movie("88", "Seja sempre livre","1968", 128554.0, "3772525");
		Movie movie6 = new Movie("18", "Te pego na saída","1988", 8554.0, "2525");
		movies.add(movie2);
		movies.add(movie);
		movies.add(movie3);
		movies.add(movie4);
		movies.add(movie5);
		movies.add(movie6);
		return movies;
	}

}
