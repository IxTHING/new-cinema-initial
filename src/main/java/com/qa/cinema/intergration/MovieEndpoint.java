package com.qa.cinema.intergration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.qa.cinema.buisness.repository.MovieService;
import com.qa.cinema.persistence.Movie;
import com.qa.cinema.util.JSONUtil;

@Path("/stock")
public class MovieEndpoint {
	
	@Inject
	private MovieService movieService;
	@Inject
	JSONUtil jsonUtil = new JSONUtil();
	
	@GET
	@Path("{id}")
	public String getMovie(
			@PathParam("id") Long id) {
		Movie movie = movieService.findMovie(id);
			return jsonUtil.getJSONForObject(movie);
	}
	
	@GET
	@Path("/json")
	public String getAllMovies() {
		List<Movie> movie = new ArrayList<Movie>();
		movie = movieService.findAllMovies();
		return jsonUtil.getJSONForObject(movie);	
	}
	
	@DELETE
	@Path("{id}")
	public void deleteMovie(
			@PathParam("id") Long id) {
			movieService.deleteMovie(id);
	}
	
	@PUT
	@Path("/json")
	public void updateMovie(String movie) {
			movieService.updateMovie(movie);
	}
	
	@POST
	@Path("/json")
	public void addMovie(String movie) {
			movieService.createMovie(movie);
	}
}
