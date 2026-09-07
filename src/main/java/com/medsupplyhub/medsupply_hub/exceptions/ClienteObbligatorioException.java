package com.medsupplyhub.medsupply_hub.exceptions;

public class ClienteObbligatorioException extends RuntimeException {

	public ClienteObbligatorioException() {
		super("Per questo utente esterno è obbligatoria l'associazione ad un cliente.");
	}

}
