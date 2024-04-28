package com.cg.exception;

public class CustomerNotFoundException extends Exception{
	public CustomerNotFoundException() {
		super();
	}
	public CustomerNotFoundException(String msg) {
		super(msg);
	}

}
