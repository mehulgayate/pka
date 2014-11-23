package com.pka.entity.support;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

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
}
