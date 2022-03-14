package com.letscode.moviebattle.moviebattle.classes;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.letscode.moviebattle.moviebattle.classes.DTO.MovieSortearDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Title",
    "Year",
    "imdbRating",
    "imdbVotes",
    "imdbID"
})
public class Movie {
	@Id
	@JsonProperty("imdbID")
	private String imdbId;
	
	@JsonProperty("Title")
    private String title;
	
	@JsonProperty("Year")
    private String year;
	 
	@JsonProperty("imdbRating")
    private Double rating;
	 
	@JsonProperty("imdbVotes")
    private String votes;
	 
    private double score;

	public Movie(String imdbId, String title, String year, Double rating, String votes) {
		super();	
		this.imdbId = imdbId;
		this.title = title;
		this.year = year;
		this.rating = rating;
		this.votes = votes;
		this.score = rating * Double.parseDouble(votes);
	}
	
	public MovieSortearDTO obterMovieSortearDTO() {
        return new MovieSortearDTO(this.title, this.year, this.imdbId);
    }
}
