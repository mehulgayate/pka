package com.pka.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.evalua.entity.support.EntityBase;

@Entity
public class MetaData extends EntityBase{	
	
	private List<Movie> movies=new ArrayList<Movie>(0);	
	private String keyword;
	
	@OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.EAGER)	
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}	
	
}
