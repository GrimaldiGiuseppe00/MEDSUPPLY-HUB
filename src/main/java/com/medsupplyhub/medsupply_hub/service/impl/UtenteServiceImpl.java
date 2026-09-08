package com.medsupplyhub.medsupply_hub.service.impl;

import java.time.LocalDateTime;

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

import jakarta.transaction.Transactional;
@Service
public class UtenteServiceImpl implements UtenteService{
	
	private final UtenteRepository utenteRepository;
	private final UtenteMapper utenteMapper;
	private final ClienteRepository clienteRepository;
	private final PasswordEncoder passwordEncoder;
	

	public UtenteServiceImpl(UtenteRepository utenteRepository, UtenteMapper utenteMapper,ClienteRepository clienteRepository,PasswordEncoder passwordEncoder) {
		this.utenteRepository = utenteRepository;
		this.utenteMapper = utenteMapper;
		this.clienteRepository = clienteRepository;
		this.passwordEncoder = passwordEncoder;
	}
	@Transactional
	@Override
	public UtenteResponseDto creazioneUtenteDaAdmin(
	        CreazioneUtenteDto utenteDaUi) {

	    // verifica email già esistente
	    if (utenteRepository.existsByEmail(utenteDaUi.email())) {
	        throw new EmailGiaEsistenteException(utenteDaUi.email());
	    }

	    // se NON è CLIENTE, non deve avere clienteId
	    if (utenteDaUi.ruoloUtente() != RuoloUtente.CLIENTE
	            && utenteDaUi.clienteId() != null) {

	        throw new ClienteNonAmmessoException();
	    }

	    // DTO -> Entity
	    Utente utente =
	            utenteMapper.creazioneUtenteDtoToUtente(utenteDaUi);

	    // se è CLIENTE deve avere un Cliente associato
	    if (utenteDaUi.ruoloUtente() == RuoloUtente.CLIENTE) {

	        if (utenteDaUi.clienteId() == null) {
	            throw new ClienteObbligatorioException();
	        }

	        Cliente clienteDaDb = clienteRepository
	                .findById(utenteDaUi.clienteId())
	                .orElseThrow(() ->
	                        new ClienteNonTrovatoException(
	                                utenteDaUi.clienteId()
	                        )
	                );

	        utente.setCliente(clienteDaDb);
	        utente.setPassword(passwordEncoder.encode(utenteDaUi.password()));
	        utente.setStatoUtente(StatoUtente.ATTIVO);
	        utente.setDataCreazione(LocalDateTime.now());
	    }

	    Utente utenteSalvato = utenteRepository.save(utente);

	    return utenteMapper.utenteToUtenteResponseDto(utenteSalvato);
	}
	
	
	
	
	}



