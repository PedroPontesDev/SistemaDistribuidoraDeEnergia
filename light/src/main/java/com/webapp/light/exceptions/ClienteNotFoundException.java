package com.webapp.light.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(String msg) {
		super(msg);
	}
	
}
