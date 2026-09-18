package com.medsupplyhub.medsupply_hub.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medsupplyhub.medsupply_hub.dto.request.utente.LoginRequestDto;
import com.medsupplyhub.medsupply_hub.utils.JwtUtils;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	public AuthRestController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/login")
	public String generaTokenJwt(@RequestBody @Valid LoginRequestDto loginDto) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));
			return jwtUtils.generaToken(loginDto.email());
		} catch (Exception e) {
			throw e;
		}

	}
	@GetMapping("/verifica")
	public String vericaRequestConToken() {
		return "token valido";
}
}
