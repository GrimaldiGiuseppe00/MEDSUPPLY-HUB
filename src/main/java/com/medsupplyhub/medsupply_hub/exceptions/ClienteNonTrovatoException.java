package com.medsupplyhub.medsupply_hub.exceptions;

public class ClienteNonTrovatoException extends RuntimeException {

	public ClienteNonTrovatoException(Long clienteId) {
		super("Cliente con id " + clienteId + " non trovato.");
	
	}

}
