package com.cognnizant.learn.hospital.consumer.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {

	private static final long serialVersionUID = -7241951856064233171L;
	
	private String message;
	private HttpStatus httpStatus;
	private Throwable throwable;
	private ZonedDateTime timestamp;
	
	public ApiException(String message) {
		this.message = message;
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public ApiException(String message, Throwable throwable) {
		super();
		this.message = message;
		this.httpStatus = HttpStatus.BAD_REQUEST;
		this.throwable = throwable;
	}
	
	public ApiException(String message, HttpStatus httpStatus, Throwable throwable) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.throwable = throwable;
	}

	public String getMessage() {
		return message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	
}
