package com.ty.Exceptions;

public class UnauthorizedActionException extends RuntimeException {

	private String message;

	public UnauthorizedActionException(String message) {

		this.message = message;
	}

	@Override
	public String getMessage() {

		return message;
	}

}
