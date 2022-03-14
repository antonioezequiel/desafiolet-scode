package com.letscode.moviebattle.moviebattle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.letscode.moviebattle.moviebattle.classes.UserQuiz;

public interface UserQuizRepository extends JpaRepository<UserQuiz, String>{
	@Query(value="SELECT * FROM USER_QUIZ  order by score desc", nativeQuery = true)
	public List<UserQuiz> findAllByOrderByScoreDesc();
	
}
