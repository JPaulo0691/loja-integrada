package com.backend.lojaintegrada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")	
	private Integer id;
	
	@Column(name = "LOGIN")
	@NotEmpty(message = "{campo.login.obrigatorio}")
	private String login;
	
	@Column(name = "SENHA")
	@NotEmpty(message = "{campo.senha.obrigatorio}")
	private String senha;
	
	@Column(name = "ADMIN")
	private boolean admin;
	
}
