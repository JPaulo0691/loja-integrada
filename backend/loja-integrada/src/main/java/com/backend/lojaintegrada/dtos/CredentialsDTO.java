package com.backend.lojaintegrada.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredentialsDTO {
	
	private String login;
	private String senha;

}
