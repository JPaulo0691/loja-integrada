package com.backend.lojaintegrada.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.lojaintegrada.security.jwt.JwtAuthFilter;
import com.backend.lojaintegrada.security.jwt.JwtService;
import com.backend.lojaintegrada.service.UsuarioServiceImpl;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Lazy //usar essa anotação para n dar problema de ciclo
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	//Esse método serve para encriptografar e para descriptografar a senha de um usuário
	@Bean
	public PasswordEncoder passwordEnconder() {
		
		/* O Bcrypt funciona da seguinte forma, toda vez que o usuário passa a senha ele gera um hash, 
		  sendo este hash sempre diferente para toda vez que a senha é gerada.		  
		*/
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioService);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		// Aqui é onde o usuário vai verificar a senha, de onde é que vamos buscar os usuários...
		// porém para ele funcionar ele necessita de um passwordEncoder(método acima)
		auth.userDetailsService(usuarioService)
			.passwordEncoder(passwordEnconder());
	}
	
	//Configuração de autorização das urls
	// 1 - primeira coisa a fazer desabilitar csrf
	// 2 - nesse método abaixo, ele pega os usuários que foram autenticados(método acima) e verfica se ele tem autorização
	// para acessar a página.
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/api/clientes/**")
				.hasAnyRole("USER", "ADMIN")
			.antMatchers("/api/produtos/**")
				.hasRole("ADMIN")
			.antMatchers("/api/pedidos/**")
				.hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.POST, "/api/users/**")
				.permitAll()
				.anyRequest().authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
}
