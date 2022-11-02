package com.backend.lojaintegrada.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.backend.lojaintegrada.dtos.CredentialsDTO;
import com.backend.lojaintegrada.dtos.TokenDTO;
import com.backend.lojaintegrada.exception.SenhaInvalidaException;
import com.backend.lojaintegrada.model.Usuario;
import com.backend.lojaintegrada.security.jwt.JwtService;
import com.backend.lojaintegrada.service.UsuarioServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsuarioController {
	
		private final UsuarioServiceImpl usuarioService;
		
		//criptografando a senha do usuário
		private final PasswordEncoder passwordEncoder;
		
		private final JwtService jwtService;
		
		@PostMapping
		public ResponseEntity<Object> salvar(@RequestBody @Valid Usuario usuario) {
			
			String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
			
			
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuario));
		}
		
		@PostMapping("/auth")
		public ResponseEntity<TokenDTO> autenticar(@RequestBody CredentialsDTO credential){
			
			try {
				Usuario usuario = Usuario.builder()
												 .login(credential.getLogin())
												 .senha(credential.getSenha())
												 .build();
				
				UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);				
				String token = jwtService.gerarToken(usuario);
				
				return ResponseEntity.status(HttpStatus.OK).body(new TokenDTO(usuario.getLogin(), token));
				
			} catch (UsernameNotFoundException |SenhaInvalidaException e) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
			}
		}
	
}
