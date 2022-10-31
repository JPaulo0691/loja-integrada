package com.backend.lojaintegrada.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.lojaintegrada.model.Usuario;
import com.backend.lojaintegrada.service.UsuarioServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsuarioController {
	
		private final UsuarioServiceImpl usuarioService;
		
		//criptografando a senha do usuário
		private final PasswordEncoder passwordEncoder;
		
		@PostMapping
		public ResponseEntity<Object> salvar(@RequestBody @Valid Usuario usuario) {
			
			String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
			
			
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuario));
		}
	
}
