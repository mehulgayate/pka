package com.pka.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import com.evalua.entity.support.EntityBase;

@Entity
public class MetaData extends EntityBase{	

	private List<Movie> movies=new ArrayList<Movie>(0);	
	private String keyword;

	@ManyToMany(cascade = { CascadeType.ALL}, fetch = FetchType.EAGER)	
	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public void addMovie(Movie movie){
		boolean found=false;
		for (Movie movie2 : this.movies) {
			if(movie.getId().equals(movie2.getId())){
				found=true;
				break;
			}
		}
		if(!found){
			System.out.println("Adding new movie to index : "+movie.getName() );
			this.movies.add(movie);
		}
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}	

}
