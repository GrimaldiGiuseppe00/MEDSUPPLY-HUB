package com.medsupplyhub.medsupply_hub.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.medsupplyhub.medsupply_hub.enums.RuoloUtente;
import com.medsupplyhub.medsupply_hub.enums.StatoUtente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente")
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String cognome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RuoloUtente ruolo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoUtente stato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_creazione", nullable = false)
    private LocalDateTime dataCreazione;

    @Column(name = "ultimo_accesso")
    private LocalDateTime ultimoAccesso;
    
    
	public Utente(Long id, String email, String password, String nome, String cognome, RuoloUtente ruolo,
			StatoUtente stato, Cliente cliente, LocalDateTime dataCreazione, LocalDateTime ultimoAccesso) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.ruolo = ruolo;
		this.stato = stato;
		this.cliente = cliente;
		this.dataCreazione = dataCreazione;
		this.ultimoAccesso = ultimoAccesso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public RuoloUtente getRuolo() {
		return ruolo;
	}

	public void setRuolo(RuoloUtente ruolo) {
		this.ruolo = ruolo;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public LocalDateTime getUltimoAccesso() {
		return ultimoAccesso;
	}

	public void setUltimoAccesso(LocalDateTime ultimoAccesso) {
		this.ultimoAccesso = ultimoAccesso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, dataCreazione, email, id, nome, password, ruolo, stato, ultimoAccesso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataCreazione, other.dataCreazione)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(password, other.password) && ruolo == other.ruolo
				&& stato == other.stato && Objects.equals(ultimoAccesso, other.ultimoAccesso);
	}
    
    
    
    
    
    
}
