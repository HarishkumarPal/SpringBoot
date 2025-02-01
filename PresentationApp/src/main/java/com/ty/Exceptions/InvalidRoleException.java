package com.ty.Exceptions;

public class InvalidRoleException extends RuntimeException {

	private String message;

	public InvalidRoleException(String message) {

		this.message = message;
	}

	@Override
	public String getMessage() {

		return message;
	}

}
