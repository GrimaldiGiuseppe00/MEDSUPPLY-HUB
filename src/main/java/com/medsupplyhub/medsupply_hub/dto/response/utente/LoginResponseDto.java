package com.medsupplyhub.medsupply_hub.dto.response.utente;

import java.util.Set;

public record LoginResponseDto(
		String token,
		String email,
		String tipoAuth,
		String emesso,
		String scadenzaToken,
		Set<String> ruoloUtente) {

	

}
