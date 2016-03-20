package com.hades.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Invalid token")
public class InvalidTokenException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}