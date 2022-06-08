package com.letscode.moviebattle.moviebattle.classes.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRankingDTO {
	private int id;
	private String userQuiz;
	private int score;
	
	public MovieRankingDTO(String userQuiz, int score, int id) {
		super();
		this.userQuiz = userQuiz;
		this.score = score;
		this.id = id;
	}	
}
