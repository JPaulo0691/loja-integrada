package com.backend.lojaintegrada.exception;

public class SenhaInvalidaException extends RuntimeException {
	
	public SenhaInvalidaException() {
		super("Senha Inválida");
	}

}
