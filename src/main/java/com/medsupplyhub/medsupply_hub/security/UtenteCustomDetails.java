package com.medsupplyhub.medsupply_hub.security;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.medsupplyhub.medsupply_hub.model.Utente;

public class UtenteCustomDetails implements UserDetails {
	private final Utente utente;

	public UtenteCustomDetails(Utente utente) {
		this.utente = utente;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return UserDetails.super.isEnabled();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("ROLE_"+utente.getRuoloUtente().name()));
	}

	@Override
	public @Nullable String getPassword() {
	return utente.getPassword();
	}

	@Override
	public String getUsername() {
		return utente.getEmail();
	}

}
