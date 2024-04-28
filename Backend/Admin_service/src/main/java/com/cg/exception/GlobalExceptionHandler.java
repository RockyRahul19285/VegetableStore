package com.cg.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ErrorDetails> adminNotFoundExceptionHandler(AdminNotFoundException ex, WebRequest request){
		ErrorDetails err = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
	}
}
