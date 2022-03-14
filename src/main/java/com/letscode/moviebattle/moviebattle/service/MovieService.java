package com.letscode.moviebattle.moviebattle.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.letscode.moviebattle.moviebattle.classes.Movie;
import com.letscode.moviebattle.moviebattle.classes.DTO.MovieSortearDTO;
import com.letscode.moviebattle.moviebattle.repository.MovieRepository;

@Service
public class MovieService {
//	@Autowired
	MovieRepository movieRepository;
//	@Autowired
//	@Lazy
	QuizService quizService;

	public MovieService() throws IOException {
		super();
	}
	
	
	@Autowired
	public MovieService(MovieRepository movieRepository, @Lazy QuizService quizService) throws IOException {
		super();
		this.movieRepository = movieRepository;
		this.quizService = quizService;
	}



	final Document document = Jsoup
			.connect("https://www.imdb.com/search/title/?groups=top_250&sort=user_rating,desc&view=advanced")
			.timeout(6000).get();
	final Document document2 = Jsoup
			.connect("https://www.imdb.com/search/title/?groups=top_250&sort=user_rating,desc&start=51&ref_=adv_nxt")
			.timeout(6000).get();
	
	public void carregarFilmes() {
		final String uri = ("http://omdbapi.com/?apikey=1c8b15a0&t=");
		RestTemplate restTemplate = new RestTemplate();
		List<String> filmes = Arrays.asList("blade", "batman", "superman", "Don't Look Up", "Reacher", "dog", "belfast",
				"CODA", "Grey's Anatomy", "cat", "Anatomy", "truman", "house", "war", "soldier", "love");

		for (String filme : filmes) {
			Movie movie = restTemplate.getForObject(uri + filme, Movie.class);
			var score = movie.getRating() * Double.parseDouble(movie.getVotes().replace(",", ""));
			movie.setScore(score);
			movieRepository.save(movie);
		}
	}

	// ---------------------------------------------------------------------------------------------
	
	public void carregarFilmesIMDB() {
		carregaFilmes(document);
		carregaFilmes(document2);
	}
	
	private void carregaFilmes(Document document) {
		Elements body = document.select("div.lister-list");
		for (Element row : body.select("div.lister-item-content")) {
			final String imdbId = row.select("h3.lister-item-header span").get(0).text().replaceAll("[\\.]", "");
			final String title = row.select("h3.lister-item-header a").text();
			final String year = row.select("h3.lister-item-header span").get(1).text().replaceAll("[^\\d]", "");
			final Double rating = Double.parseDouble(row.select("div.ratings-bar strong").text());
			final String votes = row.select("p.sort-num_votes-visible span").get(1).text().replaceAll(",", "");

			Movie movie = new Movie(imdbId, title, year, rating, votes);
			var score = movie.getRating() * Double.parseDouble(movie.getVotes().replace(",", ""));
			movie.setScore(score);
			movieRepository.save(movie);
		}
	}

	// ------------------------------------------------------------------------------------------------

	public List<MovieSortearDTO> sortearDoisFilmes() {
		List<Movie> moviies = movieRepository.findAll();
		List<MovieSortearDTO> movi = new ArrayList<MovieSortearDTO>();
		Random r = new Random();

		for (int i = 0; i < 2; i++) {
			int index1 = r.nextInt(moviies.size() - 1);
			MovieSortearDTO moviDTO = moviies.get(index1).obterMovieSortearDTO();
			movi.add(moviDTO);
		}
		quizService.setJogadaAtiva(true);
		quizService.setMovies(movi);
		return movi;
	}
	

	public Movie findById(String imdbID) {
		return movieRepository.findById(imdbID).get();
	}
	
	public List<Movie> geraListMovies(List<MovieSortearDTO> moviesDTO) {
		List<Movie> movies = new ArrayList<Movie>();
		moviesDTO.stream().forEach(m -> {
			movies.add(findById(m.getImdbID()));
		});
		
		return movies.stream().sorted(Comparator.comparing(Movie::getScore).reversed())
				.collect(Collectors.toList());		 
	}

}
