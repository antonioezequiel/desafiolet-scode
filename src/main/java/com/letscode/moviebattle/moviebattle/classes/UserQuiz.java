package com.letscode.moviebattle.moviebattle.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.letscode.moviebattle.moviebattle.classes.DTO.MovieRankingDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserQuiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String userQuiz;
    private double score;
    @Transient
    private Integer life;
    @Transient
    private String message;
    @Transient
    private int round;
    
    
    public MovieRankingDTO obterUsuarioDTO() {
        return new MovieRankingDTO(this.userQuiz, this.score, this.id);
    }

	public UserQuiz(Integer id, String userQuiz, double score, Integer life, String message, int round) {
		super();
		this.id = id;
		this.userQuiz = userQuiz;
		this.score = score;
		this.life = life;
		this.message = message;
		this.round = round;
	}    
 }
