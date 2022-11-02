package com.backend.lojaintegrada.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.backend.lojaintegrada.LojaIntegradaApplication;
import com.backend.lojaintegrada.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	
	public String gerarToken(Usuario usuario) {
		
		long expString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
		
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
		return Jwts
				.builder()
				.setSubject(usuario.getLogin())// identificação para o usuário
				.setExpiration(data)
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura)// ver substituto dessa depreciação
				.compact();
	}
	
	// esse métod obtém infos do token
	public Claims obterClaims(String token) throws ExpiredJwtException{
		
		return Jwts
				.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean tokenValido(String token) {
		
		try {			
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = dataExpiracao.toInstant()
										  .atZone(ZoneId.systemDefault()).toLocalDateTime();
			
			return !LocalDateTime.now().isAfter(data); // A data da hora atual, não é dps da data de expiração do token
			
		}catch(Exception e) {
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException{
		
		return (String) obterClaims(token).getSubject();
	}

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(LojaIntegradaApplication.class);		
		JwtService jwt = context.getBean(JwtService.class);
		
		Usuario usuario = Usuario.builder().login("paulo").build();
		String token = jwt.gerarToken(usuario);
		
		System.out.println("Token Gerado : \n" + token);
		
		System.out.println();
		boolean validToken = jwt.tokenValido(token);
		System.out.println("Token é valido: " + validToken);
		
		
		String obterToken = jwt.obterLoginUsuario(token);
		System.out.println("Login Usuário: " + obterToken);		

	}

}
