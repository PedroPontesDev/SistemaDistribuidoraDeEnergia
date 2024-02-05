package com.webapp.light.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClinteRequiredIsNullException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ClinteRequiredIsNullException(String msg) {
		super(msg);
	}
}
