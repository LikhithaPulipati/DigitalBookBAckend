package com.digitalbooks.exception;

public class BookMicroserviceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String message;

	public String getMessage() {
		return message;
	}

	public BookMicroserviceException(String message) {
		super();
		this.message = message;
	}

}
