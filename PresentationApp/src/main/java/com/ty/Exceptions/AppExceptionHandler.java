package com.ty.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<String> catchUserNotFound(UserNotFound message) {

		return new ResponseEntity<String>(message.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InvalidRoleException.class)
	public ResponseEntity<String> catchInvalidRoleException(InvalidRoleException message) {

		return new ResponseEntity<String>(message.getMessage(), HttpStatus.NOT_ACCEPTABLE);

	}

	@ExceptionHandler(RatingNotFound.class)
	public ResponseEntity<String> catchRatingNotFound(RatingNotFound message) {

		return new ResponseEntity<String>(message.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(UnauthorizedActionException.class)
	public ResponseEntity<String> catchUnauthorizedActionException(UnauthorizedActionException message) {

		return new ResponseEntity<String>(message.getMessage(), HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler(PresentationNotFound.class)
	public ResponseEntity<String> catchPresentationNotFound(PresentationNotFound message) {

		return new ResponseEntity<String>(message.getMessage(), HttpStatus.NOT_FOUND);

	}
}
