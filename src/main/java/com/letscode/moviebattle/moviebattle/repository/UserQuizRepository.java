package com.letscode.moviebattle.moviebattle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.letscode.moviebattle.moviebattle.classes.UserQuiz;

public interface UserQuizRepository extends JpaRepository<UserQuiz, String>{
	@Query(value="SELECT * FROM USER_QUIZ order by score desc", nativeQuery = true)
	public List<UserQuiz> findAllByOrderByScoreDesc();
	
	@Query(value="SELECT * FROM USER_QUIZ Order by score desc LIMIT :inicio, :quant", nativeQuery = true)
	public List<UserQuiz> findAllWithPagination(@Param("inicio") int inicio,
													@Param("quant")int quant);
	
}
