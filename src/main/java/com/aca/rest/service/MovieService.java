package com.aca.rest.service;

import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import com.aca.rest.dao.MovieDAO;
import com.aca.rest.model.Movie;
import com.aca.rest.model.genreEnum.Genre;
import com.aca.rest.model.Error;
public class MovieService {

	public static List<Movie> getAllMovies(){
		return MovieDAO.getAllMovies();
	}
	
	public static List<Movie> getByGenre(String genreString){
		Genre genre = validateGenre(genreString);
		return MovieDAO.getByGenre(genre);
	}

	public static List<Movie> getByReleaseYear(int releaseYear){
	return MovieDAO.getByReleaseYear(releaseYear);
}

	private static Genre validateGenre(String genreString) {
		boolean isValid = Genre.isValid(genreString);
		
		if(isValid) {
			return Genre.valueOf(genreString.toUpperCase());
		} else {
			
			Error error = new Error(123, "invalid value for genre '" + genreString + "', valid values are 'action','syfy', and 'thriller'");
			
			Response response = Response.status(400)
				.entity(error)
				.build();
				throw new WebApplicationException(response);
		}
	}

	public static  void addMovies(Movie newMovie) {
		
		boolean isAdded = MovieDAO.addMovie(newMovie);
		
		if(!isAdded) {
			Error error = new Error(124,"invalid value for eidr '" + newMovie.getEidr() + "', value must be unique'" );
			
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
	}

	public static void updateMovies(Movie updateMovie) {
		
		Movie movie = MovieDAO.getMovieByEidr(updateMovie.getEidr());
		
		if(movie != null) {
			MovieDAO.updateMovie(updateMovie);
		} else {
Error error = new Error(124,"invalid value for eidr or not found '" + updateMovie.getEidr() + "', '" );
			
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
	}

	public static Movie deleteMovie(String eidr) {
		return MovieDAO.deleteMovie(eidr);
	}
	
}
