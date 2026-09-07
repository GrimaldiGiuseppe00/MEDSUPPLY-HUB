package com.medsupplyhub.medsupply_hub.exceptions;

public class RuoloNonValidoException extends RuntimeException{

	public RuoloNonValidoException(String ruoloUtente) {
		super("Il ruolo "+ ruoloUtente + " non è valido.");
	}

}
