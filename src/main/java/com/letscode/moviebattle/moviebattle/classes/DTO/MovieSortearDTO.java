package com.letscode.moviebattle.moviebattle.classes.DTO;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSortearDTO {
	private String title;
	private String year;
	private String imdbID;
	
	public MovieSortearDTO(String title, String year, String imdbID) {
		super();
		this.title = title;
		this.year = year;
		this.imdbID = imdbID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(imdbID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieSortearDTO other = (MovieSortearDTO) obj;
		return Objects.equals(imdbID, other.imdbID);
	}
}
