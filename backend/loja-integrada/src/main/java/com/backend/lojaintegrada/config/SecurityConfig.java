package com.backend.lojaintegrada.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//Esse método serve para encriptografar e para descriptografar a senha de um usuário
	@Bean
	public PasswordEncoder passwordEnconder() {
		
		/* O Bcrypt funciona da seguinte forma, toda vez que o usuário passa a senha ele gera um hash, 
		  sendo este hash sempre diferente para toda vez que a senha é gerada.		  
		*/
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		
		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEnconder())
			.withUser("joao")
			.password(passwordEnconder().encode("1234"))
			.roles("USER");
	}
	
	//Configuração de autorização das urls
	// 1 - primeira coisa a fazer desabilitar csrf
	//
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("api/clientes/**")
			.authenticated() //para acessar esta url é necessário está autenticado ou posso usar uma role para acessar tal url
			.and()
			.formLogin();
	}
	
}
