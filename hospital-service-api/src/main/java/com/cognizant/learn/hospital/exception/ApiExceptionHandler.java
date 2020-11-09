package com.cognizant.learn.hospital.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cognizant.learn.hospital.beans.ApiError;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value= {ApiException.class})
	public ResponseEntity<Object> handleApiException(ApiException e){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiError apiError = new ApiError(e.getMessage(), httpStatus, ZonedDateTime.now());
		
		return new ResponseEntity<>(apiError, httpStatus);
	}
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleException(String message){
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ApiError apiError = new ApiError(message, httpStatus, ZonedDateTime.now());
		
		return new ResponseEntity<>(apiError, httpStatus);
	}
	
}
