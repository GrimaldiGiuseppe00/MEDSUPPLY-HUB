package com.medsupplyhub.medsupply_hub.exceptions;

public class ClienteNonAmmessoException extends RuntimeException {

	public ClienteNonAmmessoException() {
		super("Per questo utente interno non è ammesso associare un cliente esterno.");
	}

}
