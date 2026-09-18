package com.medsupplyhub.medsupply_hub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.medsupplyhub.medsupply_hub.filters.JwtAuthFilter;

import static org.springframework.security.config.Customizer.withDefaults;;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;

	
	public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}






	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/v1/auth/login").permitAll()
				.anyRequest().authenticated());
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}






	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean 
	public AuthenticationManager authenticationManager(UtenteCustomDetailsServiceImpl customUtenteDetailsService,PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider(customUtenteDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(daoAuthenticationProvider);
	}




}
