package com.cg.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	
}
