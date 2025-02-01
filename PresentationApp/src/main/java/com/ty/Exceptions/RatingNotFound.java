package com.ty.Exceptions;

public class RatingNotFound extends RuntimeException {
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public RatingNotFound(String message) {
		this.message = message;
	}

}
