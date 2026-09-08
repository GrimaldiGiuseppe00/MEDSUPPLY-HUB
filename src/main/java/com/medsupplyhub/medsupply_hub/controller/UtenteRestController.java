package com.medsupplyhub.medsupply_hub.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medsupplyhub.medsupply_hub.dto.request.utente.CreazioneUtenteDto;
import com.medsupplyhub.medsupply_hub.dto.response.utente.UtenteResponseDto;
import com.medsupplyhub.medsupply_hub.service.impl.UtenteServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/utenti")
public class UtenteRestController {
	private UtenteServiceImpl utenteService;
	
	public UtenteRestController(UtenteServiceImpl utenteService) {
		this.utenteService = utenteService;
	}

	@PostMapping
	public ResponseEntity<UtenteResponseDto> creazioneUtenteDaAdmin(@Valid CreazioneUtenteDto utenteDaUi) {
		UtenteResponseDto utente=utenteService.creazioneUtenteDaAdmin(utenteDaUi);
		return new ResponseEntity<UtenteResponseDto>(utente, HttpStatus.CREATED);
		
	}

}
