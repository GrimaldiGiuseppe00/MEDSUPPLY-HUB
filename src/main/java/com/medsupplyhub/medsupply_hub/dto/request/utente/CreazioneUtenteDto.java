package com.medsupplyhub.medsupply_hub.dto.request.utente;
import com.medsupplyhub.medsupply_hub.enums.RuoloUtente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreazioneUtenteDto(
		@NotBlank
		String nome,
		@NotBlank
		String cognome,
		@NotBlank
		@Email
		String email,
		@Size(min = 6,max =20,message = "La password deve contenere un minimo di 6 caratteri e un massimo di 20." )
		String password,
		@NotNull
		RuoloUtente ruoloUtente,
		@Positive
		Long clienteId,
		@Pattern(
			    regexp = "^(\\+39)?\\s?3\\d{8,9}$",
			    message = "Numero di telefono non valido.Controllare cifre prefisso nazionale"
			)
		@Size(max = 10,message = "Numero di telefono non valido")
		Integer telefono) {
}
