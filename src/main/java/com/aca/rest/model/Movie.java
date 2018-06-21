package com.aca.rest.model;

import com.aca.rest.model.genreEnum.Genre;

public class Movie {
	
	private String eidr;
	private String title;
	private Genre genre;
	private int releaseYear;
	
	
	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Movie (String eidr, String title, Genre genre, int releaseYear) {
		this.title = title;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.eidr = eidr;
	}

	public Movie() {
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getEidr() {
		return eidr;
	}

	public void setEidr(String eidr) {
		this.eidr = eidr;
	}
	
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("eidr: " + getEidr());
		stringBuffer.append(", title: " + getTitle());
		stringBuffer.append(", genre: " + getGenre());
		stringBuffer.append(", release year: " + getReleaseYear());
		
		return stringBuffer.toString();
	}

}
