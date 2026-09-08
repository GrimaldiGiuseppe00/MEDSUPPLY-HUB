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
    private RuoloUtente ruoloUtente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoUtente statoUtente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_creazione", nullable = false)
    private LocalDateTime dataCreazione;
    
    @Column(name = "telefono",nullable = true)
    private String telefono;

    @Column(name = "ultimo_accesso")
    private LocalDateTime ultimoAccesso;
    
    
	public Utente(Long id, String email, String password, String nome, String cognome, RuoloUtente ruoloUtente,
			StatoUtente statoUtente, Cliente cliente, LocalDateTime dataCreazione, LocalDateTime ultimoAccesso,String telefono) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.ruoloUtente = ruoloUtente;
		this.statoUtente = statoUtente;
		this.cliente = cliente;
		this.dataCreazione = dataCreazione;
		this.ultimoAccesso = ultimoAccesso;
		this.telefono=telefono;
	}
	public Utente() {
		
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

	public RuoloUtente getRuoloUtente() {
		return ruoloUtente;
	}

	public void setRuoloUtente(RuoloUtente ruolo) {
		this.ruoloUtente = ruolo;
	}

	public StatoUtente getStatoUtente() {
		return statoUtente;
	}

	public void setStatoUtente(StatoUtente stato) {
		this.statoUtente = stato;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, dataCreazione, email, id, nome, password, ruoloUtente, ruoloUtente, telefono, ultimoAccesso);
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
				&& Objects.equals(nome, other.nome) && Objects.equals(password, other.password) && ruoloUtente == other.ruoloUtente
				&& statoUtente == other.statoUtente && Objects.equals(telefono, other.telefono)
				&& Objects.equals(ultimoAccesso, other.ultimoAccesso);
	}

	
	

    
    
    
    
    
    
}
