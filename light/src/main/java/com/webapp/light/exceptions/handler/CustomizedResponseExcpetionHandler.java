package com.webapp.light.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.webapp.light.exceptions.ClienteNotFoundException;
import com.webapp.light.exceptions.ClinteRequiredIsNullException;
import com.webapp.light.exceptions.ExceptionResponse;

@ControllerAdvice
@RestController
public class CustomizedResponseExcpetionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleClienteNotFound(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ClinteRequiredIsNullException.class)
	public ResponseEntity<ExceptionResponse> handleRequiredClientIsNull(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
