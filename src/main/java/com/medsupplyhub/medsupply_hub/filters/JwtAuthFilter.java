package com.medsupplyhub.medsupply_hub.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.medsupplyhub.medsupply_hub.security.UtenteCustomDetailsServiceImpl;
import com.medsupplyhub.medsupply_hub.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter  extends OncePerRequestFilter{
	
	private final JwtUtils jwtUtils;
	private final UtenteCustomDetailsServiceImpl utenteCustomDetailsService;
	
	public JwtAuthFilter(JwtUtils jwtUtils,UtenteCustomDetailsServiceImpl utenteCustomDetailsService) {
		this.jwtUtils = jwtUtils;
		this.utenteCustomDetailsService=utenteCustomDetailsService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader("Authorization");
		String tokenJwt=null;
		String nomeUtenteToken=null;
		//controllo esistenza header authorization e token
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			//estrazione token
			tokenJwt= authHeader.substring(7);
			//estrazione nome utente(subject nel payload) token jwt
			nomeUtenteToken=jwtUtils.estraiNomeUtenteToken(tokenJwt);
		}
		
		if (nomeUtenteToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			//recupero Utente da Db
			UserDetails utenteDetails= utenteCustomDetailsService.loadUserByUsername(nomeUtenteToken);
			//validiamo il token per utente e scadenza
			if(jwtUtils.validaToken(nomeUtenteToken,utenteDetails,tokenJwt)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(utenteDetails,null,utenteDetails.getAuthorities());
				//aggiungiamo informazioni aggiuntive riguardanti la request http
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			//continua la catena di fitri per la request
			filterChain.doFilter(request, response);
		}
		
		
	}

}
