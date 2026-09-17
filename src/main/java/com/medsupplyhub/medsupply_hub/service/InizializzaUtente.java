package com.medsupplyhub.medsupply_hub.service;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.medsupplyhub.medsupply_hub.enums.RuoloUtente;
import com.medsupplyhub.medsupply_hub.enums.StatoUtente;
import com.medsupplyhub.medsupply_hub.model.Utente;
import com.medsupplyhub.medsupply_hub.repository.UtenteRepository;

@Component
public class InizializzaUtente {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public InizializzaUtente(
            UtenteRepository utenteRepository,
            PasswordEncoder passwordEncoder) {

        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner run() {

        return args -> {
        	if (utenteRepository.findByEmail("admin@medsupply.it").isEmpty()) {
				
        		Utente admin = new Utente();
        		
        		admin.setNome("Giuseppe");
        		admin.setCognome("Grimaldi");
        		admin.setEmail("admin@medsupply.it");
        		admin.setDataCreazione(LocalDateTime.now());
        		
        		// IMPORTANTISSIMO per Spring Security
        		admin.setPassword(
        				passwordEncoder.encode("Admin123!")
        				);
        		
        		admin.setRuoloUtente(RuoloUtente.ADMIN);
        		admin.setStatoUtente(StatoUtente.ATTIVO);
        		
        		utenteRepository.save(admin);
        		System.out.println("Creato admin di prova");
        	}
        };
    }
}