package com.medsupplyhub.medsupply_hub.utils;

import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtils {

	@Value("${security.jwt.scadenza}")
	private long jwtScadenzaInMs;

	@Value("${security.jwt.secret}")
	private String jwtSecret;

	private SecretKey jwtChiaveSegreta;

	private static final Logger log =
			LoggerFactory.getLogger(JwtUtils.class);

	private static final DateTimeFormatter FORMATTER =
	        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
	                .withZone(ZoneId.of("Europe/Rome"));
	@PostConstruct
	public void init() {

		this.jwtChiaveSegreta = Keys.hmacShaKeyFor(
				jwtSecret.getBytes(StandardCharsets.UTF_8)
				);

		log.debug("Chiave JWT inizializzata correttamente");
	}


	public String generaToken(String username) {

		String token = Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(
						new Date(
								System.currentTimeMillis()
								+ jwtScadenzaInMs
								)
						)
				.signWith(jwtChiaveSegreta)
				.compact();

		log.debug("Token JWT generato per utente {}", username);

		return token;
	}


	public String estraiNomeUtenteToken(String token) {

		Claims payloadToken = estraiPayloadToken(token);

		String username = payloadToken.getSubject();

		log.debug("Estratto username dal JWT");

		return username;
	}


	public Claims estraiPayloadToken(String token) {

		try {

			Claims payloadToken = Jwts.parser()
					.verifyWith(jwtChiaveSegreta)
					.build()
					.parseSignedClaims(token)
					.getPayload();

			log.debug("Payload JWT estratto correttamente");

			return payloadToken;

		} catch (ExpiredJwtException e) {

			log.warn("JWT scaduto");
			throw e;

		} catch (UnsupportedJwtException e) {

			log.warn("Formato JWT non supportato");
			throw e;

		} catch (MalformedJwtException e) {

			log.warn("JWT malformato");
			throw e;

		} catch (SecurityException e) {

			log.warn("Firma JWT non valida");
			throw e;

		} catch (JwtException e) {

			log.warn("JWT non valido");
			throw e;
		}
	}


	public boolean validaToken(
			String username,
			UserDetails utenteDetails,
			String tokenJwt) {

		boolean usernameValido =
				username.equals(utenteDetails.getUsername());

		if (!usernameValido) {

			log.warn(
					"Username del JWT non corrisponde all'utente autenticato"
					);

			return false;
		}

		boolean tokenValido = !isTokenExpired(tokenJwt);

		if (tokenValido) {

			log.debug(
					"JWT validato correttamente per utente {}",
					username
					);

		} else {

			log.warn(
					"Validazione JWT fallita per utente {}",
					username
					);
		}

		return tokenValido;
	}


	private boolean isTokenExpired(String tokenJwt) {

		Date expiration =
				estraiPayloadToken(tokenJwt).getExpiration();

		boolean tokenScaduto =
				expiration.before(new Date());

		if (tokenScaduto) {

			log.warn("JWT scaduto");

		} else {

			log.debug("JWT non scaduto");
		}

		return tokenScaduto;
	}

	public String formattaData(Date data) {
	    return FORMATTER.format(data.toInstant());
	}
	

}