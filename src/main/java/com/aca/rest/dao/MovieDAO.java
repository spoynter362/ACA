package com.aca.rest.dao;

import java.util.ArrayList;
import java.util.List;

import com.aca.rest.model.Movie;
import com.aca.rest.model.genreEnum.Genre;

public class MovieDAO {

	private static List<Movie> movies = new ArrayList<Movie>();
	
	static {
		movies.add(new Movie("asd5f48", "The World is Not Enough", Genre.ACTION, 1995));
		movies.add(new Movie("asd126591d", "Star Trek", Genre.SYFY, 1985));
		movies.add(new Movie("sd54f8", "The Dark Knight", Genre.ACTION, 2009));
		movies.add(new Movie("sadf515", "A Quiet Place", Genre.THRILLER, 2018));
	}
	
	public static List<Movie> getAllMovies(){
		List <Movie> movies = new ArrayList<Movie>();
		movies.addAll(MovieDAO.movies);
		
		return movies;
	}
	
	public static List<Movie> getByGenre(Genre genre){
		
		List<Movie> myMovies = new ArrayList<Movie>();
		
		for (Movie movie: MovieDAO.movies) {
			if (movie.getGenre().equals(genre)) {
				myMovies.add(movie);
			}
		}
		return myMovies;
	}
	
public static List<Movie> getByReleaseYear(int releaseYear){
		
		List<Movie> myMovies = new ArrayList<Movie>();
		
		for (Movie movie: MovieDAO.movies) {
			if (movie.getReleaseYear() == releaseYear) {
				myMovies.add(movie);
			}
		}
		
		return myMovies;
	}

public static Movie getMovieByEidr(String eidr) {

	for (Movie movie : MovieDAO.movies) {
		if (movie.getEidr().equals(eidr)) {
			return movie;
		}
	}
	
	return null;
}
	
	public static boolean addMovie(Movie newMovie) {
		boolean added = false;
		
		synchronized (MovieDAO.movies){
			Movie movie = MovieDAO.getMovieByEidr(newMovie.getEidr());
			if (null == movie) {
		MovieDAO.movies.add(newMovie);
		added = true;
			}
		}
		return added;
	}

	public static void updateMovie(Movie updateMovie) {
		
		
		
		for (Movie movie : MovieDAO.movies) {
			if (movie.getEidr().equals(updateMovie.getEidr())) {


					MovieDAO.movies.remove(movie);
					MovieDAO.movies.add(updateMovie);
					break;
			}
		}
	}
	
	public static Movie deleteMovie(String eidr) {
		Movie foundMovie = null;
		
	
		for (Movie movie : MovieDAO.movies) {
			if (movie.getEidr().equals(eidr)) {


					MovieDAO.movies.remove(movie);
					foundMovie = movie;
					break;
			}
		}
		return foundMovie;
}
}
	