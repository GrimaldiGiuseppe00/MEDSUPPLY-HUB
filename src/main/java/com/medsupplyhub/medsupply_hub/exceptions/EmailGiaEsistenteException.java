package com.medsupplyhub.medsupply_hub.exceptions;

public class EmailGiaEsistenteException extends RuntimeException{

	public EmailGiaEsistenteException(String email) {
		super("Utente con email "+ email + " già esistente.");
	}

}
