package com.medsupplyhub.medsupply_hub.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.medsupplyhub.medsupply_hub.dto.request.utente.LoginRequestDto;
import com.medsupplyhub.medsupply_hub.dto.response.utente.LoginResponseDto;
import com.medsupplyhub.medsupply_hub.security.UtenteCustomDetails;
import com.medsupplyhub.medsupply_hub.service.AuthService;
import com.medsupplyhub.medsupply_hub.utils.JwtUtils;

import io.jsonwebtoken.Claims;

@Service
public class AuthServiceImpl implements AuthService {
	
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;
	
	

	public AuthServiceImpl(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
		this.jwtUtils = jwtUtils;
		this.authenticationManager = authenticationManager;
	}



	@Override
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		try {
			Authentication auth =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequestDto.email(), 
					loginRequestDto.password())
					);
			 String token=jwtUtils.generaToken(loginRequestDto.email());
			 UtenteCustomDetails utenteDetails=(UtenteCustomDetails)auth.getPrincipal();
			 Set<String> autorizzazioni = utenteDetails.getAuthorities()
					    .stream()
					    .map(autorizzazione-> autorizzazione.getAuthority())
					    .collect(Collectors.toSet());
			 Claims payloadToken=jwtUtils.estraiPayloadToken(token);
			 LoginResponseDto loginResponseDto=new LoginResponseDto(
					 token,
					 utenteDetails.getUsername(),
					 "Bearer",
					 jwtUtils.formattaData(payloadToken.getIssuedAt()),
					 jwtUtils.formattaData(payloadToken.getExpiration()),
					 autorizzazioni
					 );
			 return loginResponseDto;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
}
