package com.medsupplyhub.medsupply_hub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

	@ExceptionHandler(ClienteNonTrovatoException.class)
	public ProblemDetail handleUtenteClienteNonTrovatoException(
			ClienteNonTrovatoException ex) {
		String dettaglioEx= ex.getMessage();
		ProblemDetail pd= ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, dettaglioEx);
	    pd.setTitle("Cliente non trovato");
	    pd.setProperty("codiceErrore", "CLIENTE_NOT_FOUND");
	    return pd;
	}
	
	@ExceptionHandler(EmailGiaEsistenteException.class)
	public ProblemDetail handleEmailGiaEsistenteException(
			EmailGiaEsistenteException ex) {
		String dettaglioEx= ex.getMessage();
		ProblemDetail pd= ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, dettaglioEx);
	    pd.setTitle("Email già esistente");
	    pd.setProperty("codiceErrore", "CONFLICT");
	    return pd;
	}
	@ExceptionHandler(RuoloNonValidoException.class)
	public ProblemDetail handleRuoloNonValidoException(
			RuoloNonValidoException ex) {
		String dettaglioEx= ex.getMessage();
		ProblemDetail pd= ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, dettaglioEx);
	    pd.setTitle("Ruolo non valido");
	    pd.setProperty("codiceErrore", "BAD_REQUEST");
	    return pd;
	}
	@ExceptionHandler(ClienteObbligatorioException.class)
	public ProblemDetail handleClienteObbligatorioException(
			ClienteObbligatorioException ex) {
		String dettaglioEx= ex.getMessage();
		ProblemDetail pd= ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, dettaglioEx);
	    pd.setTitle("Cliente Obbligatorio");
	    pd.setProperty("codiceErrore", "BAD_REQUEST");
	    return pd;
	}
	@ExceptionHandler(ClienteNonAmmessoException.class)
	public ProblemDetail handleClienteNonAmmessoException(
			ClienteNonAmmessoException ex) {
		String dettaglioEx= ex.getMessage();
		ProblemDetail pd= ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, dettaglioEx);
	    pd.setTitle("Cliente Non Ammesso");
	    pd.setProperty("codiceErrore", "BAD_REQUEST");
	    return pd;
	}
	

}
