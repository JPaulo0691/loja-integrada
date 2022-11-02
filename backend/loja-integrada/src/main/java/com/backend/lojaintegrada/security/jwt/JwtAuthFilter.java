package com.backend.lojaintegrada.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.lojaintegrada.service.UsuarioServiceImpl;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	private JwtService jwtService;
	private UsuarioServiceImpl usuarioServiceImpl;
	
	public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl usuaServiceImpl) {	
		this.jwtService = jwtService;
		this.usuarioServiceImpl = usuaServiceImpl;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		
		if(authorization != null && authorization.startsWith("Bearer")) {
			
			String token = authorization.split(" ")[1];			
			boolean validToken = jwtService.tokenValido(token);
			
			if(validToken) {
				String loginUser = jwtService.obterLoginUsuario(token);
				UserDetails usuario = usuarioServiceImpl.loadUserByUsername(loginUser);
				UsernamePasswordAuthenticationToken user = 
						new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());
				
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // necessário fazer isso para dizer ao contexto que é uma autenticação web.
				
				SecurityContextHolder.getContext().setAuthentication(user);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
