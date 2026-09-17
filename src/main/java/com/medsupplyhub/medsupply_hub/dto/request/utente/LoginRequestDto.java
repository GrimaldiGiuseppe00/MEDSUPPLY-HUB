package com.medsupplyhub.medsupply_hub.dto.request.utente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
	    @NotBlank(message = "L'email è obbligatoria")
	    @Email(message = "Il formato dell'email non è valido")
	    @Size(max = 100, message = "L'email non può superare i 100 caratteri")
	    String email,

	    @NotBlank(message = "La password è obbligatoria")
	    @Size(max = 100, message = "La password non può superare i 100 caratteri")
	    String password) {
	
}
