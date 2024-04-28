package com.cg.exception;

public class AddToCartNotFoundException extends Exception{

	public AddToCartNotFoundException() {
		super();
	}
	public AddToCartNotFoundException(String msg) {
		super(msg);
	}
}
