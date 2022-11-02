package com.backend.lojaintegrada.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.lojaintegrada.exception.SenhaInvalidaException;
import com.backend.lojaintegrada.model.Usuario;
import com.backend.lojaintegrada.repository.UsuarioRepository;

import ch.qos.logback.core.encoder.Encoder;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder encrypt;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public UserDetails autenticar(Usuario usuario) {
		
		UserDetails user = loadUserByUsername(usuario.getLogin());
		boolean matchPassword = encrypt.matches(usuario.getSenha(), user.getPassword()); // compara a senha digitada com o hash gerado
		
		if(matchPassword) {
			return user;
		}
		
		throw new SenhaInvalidaException();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByLogin(username)
		.orElseThrow(
				() ->  new UsernameNotFoundException("Usuário não encontrado!")
		);
		
		String[] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return User
				.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
	}

}
