package com.aca.rest.model;

public class Error {

	private int number;
	private String message;
	
	public Error(int number, String message) {
		this.number = number;
		this.message = message;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
