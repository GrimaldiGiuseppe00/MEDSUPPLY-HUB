package com.medsupplyhub.medsupply_hub.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medsupplyhub.medsupply_hub.dto.request.utente.CreazioneUtenteDto;
import com.medsupplyhub.medsupply_hub.dto.response.utente.UtenteResponseDto;
import com.medsupplyhub.medsupply_hub.enums.RuoloUtente;
import com.medsupplyhub.medsupply_hub.enums.StatoUtente;
import com.medsupplyhub.medsupply_hub.exceptions.ClienteNonAmmessoException;
import com.medsupplyhub.medsupply_hub.exceptions.ClienteNonTrovatoException;
import com.medsupplyhub.medsupply_hub.exceptions.ClienteObbligatorioException;
import com.medsupplyhub.medsupply_hub.exceptions.EmailGiaEsistenteException;
import com.medsupplyhub.medsupply_hub.mapper.UtenteMapper;
import com.medsupplyhub.medsupply_hub.model.Cliente;
import com.medsupplyhub.medsupply_hub.model.Utente;
import com.medsupplyhub.medsupply_hub.repository.ClienteRepository;
import com.medsupplyhub.medsupply_hub.repository.UtenteRepository;
import com.medsupplyhub.medsupply_hub.service.UtenteService;
@Service
public class UtenteServiceImpl implements UtenteService{
	
	private final UtenteRepository utenteRepository;
	private final UtenteMapper utenteMapper;
	private final ClienteRepository clienteRepository;
	private final PasswordEncoder passwordEncoder;
	final static Logger logger = LoggerFactory.getLogger(UtenteServiceImpl.class);

	public UtenteServiceImpl(UtenteRepository utenteRepository, UtenteMapper utenteMapper,ClienteRepository clienteRepository,PasswordEncoder passwordEncoder) {
		this.utenteRepository = utenteRepository;
		this.utenteMapper = utenteMapper;
		this.clienteRepository = clienteRepository;
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	@org.springframework.transaction.annotation.Transactional
	public UtenteResponseDto creazioneUtenteDaAdmin(
	        CreazioneUtenteDto utenteDaUi) {
		 logger.info(
		            "Avvio creazione utente con ruolo={}",
		            utenteDaUi.ruoloUtente()
		    );
	    // verifica email già esistente
	    if (utenteRepository.existsByEmail(utenteDaUi.email())) {
	    	 logger.warn(
	                 "Creazione utente rifiutata: email già esistente"
	         );
	        throw new EmailGiaEsistenteException(utenteDaUi.email());
	    }

	    // se NON è CLIENTE, non deve avere clienteId
	    if (utenteDaUi.ruoloUtente() != RuoloUtente.CLIENTE
	            && utenteDaUi.clienteId() != null) {
	    	logger.warn(
                    "Creazione utente interno MedSupply rifiutata: clienteId non ammesso"
            );
	        throw new ClienteNonAmmessoException();
	    }

	    // DTO -> Entity
	    Utente utente =
	            utenteMapper.creazioneUtenteDtoToUtente(utenteDaUi);
	    
	    // se è CLIENTE deve avere un Cliente associato
	    if (utenteDaUi.ruoloUtente() == RuoloUtente.CLIENTE) {

	        if (utenteDaUi.clienteId() == null) {
	        	logger.warn(
	                    "Creazione utente CLIENTE rifiutata: clienteId mancante"
	            );
	            throw new ClienteObbligatorioException();
	        }

	        Cliente clienteDaDb = clienteRepository
	                .findById(utenteDaUi.clienteId())
	                .orElseThrow(() ->{
	                	logger.warn(
	                            "Creazione utente CLIENTE fallita: clienteId={} non trovato",
	                            utenteDaUi.clienteId());
	                	throw new ClienteNonTrovatoException(utenteDaUi.clienteId());
	                }
	                );

	        utente.setCliente(clienteDaDb);
	        logger.debug(
	                "Cliente id={} associato al nuovo utente",
	                clienteDaDb.getId()
	        );
	        utente.setPassword(passwordEncoder.encode(utenteDaUi.password()));
	        utente.setStatoUtente(StatoUtente.ATTIVO);
	        utente.setDataCreazione(LocalDateTime.now());
	    }

	    Utente utenteSalvato = utenteRepository.save(utente);

	    logger.info(
	            "Utente creato correttamente id={} ruolo={}",
	            utenteSalvato.getId(),
	            utenteSalvato.getRuoloUtente()
	    );

	    return utenteMapper.utenteToUtenteResponseDto(utenteSalvato);
	}
	
	
	
	
	}



