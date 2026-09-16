package com.medsupplyhub.medsupply_hub.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medsupplyhub.medsupply_hub.model.Utente;
import com.medsupplyhub.medsupply_hub.repository.UtenteRepository;
@Service
public class UtenteCustomDetailsServiceImpl implements UserDetailsService{
	
	private final UtenteRepository utenteRepository;
	
	

	public UtenteCustomDetailsServiceImpl(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	      Utente utente = utenteRepository.findByEmail(username)
	              .orElseThrow(() ->
	                  new UsernameNotFoundException("Utente non trovato"));
	      return new UtenteCustomDetails(utente);
	      
	}

}
