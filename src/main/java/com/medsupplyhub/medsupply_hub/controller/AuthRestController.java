package com.medsupplyhub.medsupply_hub.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medsupplyhub.medsupply_hub.dto.request.utente.LoginRequestDto;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
	
	private final AuthenticationManager authenticationManager;
	
	
	
	public AuthRestController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	@PostMapping("/login")
	public String generaTokenJwt(@RequestBody @Valid LoginRequestDto loginDto ) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginDto.email(),loginDto.password()));
			return "jwtToken";
		} catch (Exception e) {
			throw e;
		}
		
	}
	

}
