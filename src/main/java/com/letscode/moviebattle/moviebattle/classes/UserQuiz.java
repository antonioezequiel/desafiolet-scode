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
    private int score;
    @Transient
    private Integer life;
    @Transient
    private String message;
    @Transient
    private int round;
    @Transient
    private boolean finalizado;
    
    
    public MovieRankingDTO obterUsuarioDTO() {
        return new MovieRankingDTO(this.userQuiz, this.score, this.id);
    }

	public UserQuiz(Integer id, String userQuiz, int score, Integer life, String message, int round) {
		super();
		this.id = id;
		this.userQuiz = userQuiz;
		this.score = score;
		this.life = life;
		this.message = message;
		this.round = round;
		this.finalizado = false;
	}    
 }
