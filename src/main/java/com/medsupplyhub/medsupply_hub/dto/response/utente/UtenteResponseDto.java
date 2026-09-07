package com.medsupplyhub.medsupply_hub.dto.response.utente;

import com.medsupplyhub.medsupply_hub.enums.RuoloUtente;
import com.medsupplyhub.medsupply_hub.enums.StatoUtente;



public record UtenteResponseDto(
		Long id,
		String nome,
		String cognome,
		String email,
		RuoloUtente ruoloUtente,
		Integer telefono,
		StatoUtente statoUtente) {
}
