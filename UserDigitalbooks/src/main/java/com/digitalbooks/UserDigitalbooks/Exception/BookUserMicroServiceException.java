package com.digitalbooks.UserDigitalbooks.Exception;

public class BookUserMicroServiceException extends RuntimeException {
	private static final long serialVersionUID = 2065916542403224036L;

	
	private final String message;
		
	public String getMessage() {
		return message;
	}

	public BookUserMicroServiceException(String message) {
		super();
		this.message = message;
	}

	

}
