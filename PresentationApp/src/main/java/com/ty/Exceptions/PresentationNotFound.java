package com.ty.Exceptions;

public class PresentationNotFound extends RuntimeException {
	private String message;

	@Override
	public String getMessage() {

		return message;
	}

	public PresentationNotFound(String message) {

		this.message = message;
	}

}
