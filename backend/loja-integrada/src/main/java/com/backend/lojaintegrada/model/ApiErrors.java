package com.backend.lojaintegrada.model;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class ApiErrors {
	
	private List<String> errors;
	
	public ApiErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public ApiErrors(String mensage) {
		this.errors = Arrays.asList(mensage);
	}
	
}

