package com.backend.lojaintegrada.exception;

public class OrderNotFounException extends RuntimeException {
	
	public OrderNotFounException(String message) {
		super(message);
	}
	
}
