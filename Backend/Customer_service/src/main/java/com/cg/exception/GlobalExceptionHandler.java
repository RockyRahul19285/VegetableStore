package com.cg.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorDetails> customerNotFoundExceptionHandler(
			CustomerNotFoundException ex, WebRequest req){
		ErrorDetails err = new ErrorDetails(new Date(), ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorDetails> customExceptionHandler(
			CustomException ex, WebRequest req){
		ErrorDetails err = new ErrorDetails(new Date(), ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.FORBIDDEN);
	}
	
}