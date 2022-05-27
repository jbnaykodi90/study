package com.jay.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), "");

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), "");

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
