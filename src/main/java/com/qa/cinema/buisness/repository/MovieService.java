package com.qa.cinema.buisness.repository;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.cinema.persistence.Movie;
import com.qa.cinema.util.JSONUtil;

@Transactional(Transactional.TxType.SUPPORTS)
public class MovieService {
	
	JSONUtil jsonUtil = new JSONUtil();
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;

	public Movie findMovie(Long id)
	{
		return em.find(Movie.class, id);
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public Movie create(Movie movie)
	{
		em.persist(movie);
		return movie;
	}
	
	public List<Movie> findAllMovies()
	{
		TypedQuery<Movie> query = em.createQuery("SELECT * FROM Movie", Movie.class);
        return query.getResultList();
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void deleteMovie(Long id)
	{
		em.remove(em.find(Movie.class, id));
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void updateMovie(String movie)
	{
		Movie movieOb = jsonUtil.getObjectForJSON(movie, Movie.class);
		em.merge(movieOb);
	}
}

