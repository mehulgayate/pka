package com.pka.entity.support;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.pka.entity.GraphData;
import com.pka.entity.MetaData;
import com.pka.entity.Movie;
import com.pka.entity.User;

@Transactional
public class Repository {
	
	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public List<Movie> listAllMovies(){
		return getSession().createQuery("From "+Movie.class.getName()).list();
	}	
	
	public User findUserByEmail(String email){
		return (User) getSession().createQuery("FROM "+User.class.getName()+" where email=:email")
				.uniqueResult();
	}
	
	public List<Movie> listMoviesBySearchString(String searchString){
		return getSession().createQuery("FROM "+Movie.class.getName()+" mv where lower(mv.name) LIKE lower(:searchString) OR lower(mv.discription) LIKE lower(:searchString) order by hitScore desc")
				.setParameter("searchString", "%"+searchString+"%")
				.list();
	}
	
	public MetaData findMetaDataByKeyword(String keyword){
		return (MetaData) getSession().createQuery("FROM "+MetaData.class.getName()+" md where lower(md.keyword)=lower(:keyword)")
				.setParameter("keyword", keyword)
				.uniqueResult();
	}
	
	public Movie findMovieById(Long id){
		return (Movie) getSession().createQuery("FROM "+Movie.class.getName()+" mv where mv.id=:id")
				.setParameter("id", id)
				.uniqueResult();
	}
	
	public Movie findMovieByName(String name){
		return (Movie) getSession().createQuery("FROM "+Movie.class.getName()+" mv where mv.name=:name")
				.setParameter("name", name)
				.uniqueResult();
	}
	
	public List<GraphData> listGraphData(){
		return getSession().createQuery("FROM "+GraphData.class.getName())
				.list();
	}
	
	public List<Movie> listLatestAddedMovies(){
		return getSession().createQuery("FROM "+Movie.class.getName()+" order by id")
				.setMaxResults(10)
				.list();
	}
	
	public List<MetaData> listAllMetaData(){
		return getSession().createQuery("FROM "+MetaData.class.getName())				
				.list();
	}
}
