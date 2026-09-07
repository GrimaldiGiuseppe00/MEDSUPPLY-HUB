package com.medsupplyhub.medsupply_hub.service;

import com.medsupplyhub.medsupply_hub.dto.request.utente.CreazioneUtenteDto;
import com.medsupplyhub.medsupply_hub.dto.response.utente.UtenteResponseDto;

public interface UtenteService {
	public UtenteResponseDto  creazioneUtenteDaAdmin(CreazioneUtenteDto utenteDaUi);
}
