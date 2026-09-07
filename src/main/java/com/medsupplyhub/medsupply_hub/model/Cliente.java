package com.medsupplyhub.medsupply_hub.model;

import java.awt.Taskbar.State;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.medsupplyhub.medsupply_hub.enums.StatoCliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,length = 100)
	private String ragioneSociale;
	@Column(nullable = false,unique = true,length = 50)
	private String partitaIva;
	@Column(nullable = false,unique = true,length = 20)
	private String codiceFiscale;
	@Column(nullable = false,unique = true,length = 100)
	private String email;
	@Column(length = 100)
	private String indirizzo;
	@Column(nullable = false)
	private LocalDateTime dataCreazione;
	@Enumerated(EnumType.STRING)
	private  StatoCliente statoCliente;
	@Column(nullable = false)
	private BigDecimal limiteCredito;
	@Column(nullable = false)
	private BigDecimal creditoUtilizzato;
	
	@OneToMany(mappedBy = "cliente")
	private List<Utente> utenti = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public StatoCliente getStatoCliente() {
		return statoCliente;
	}
	public void setStatoCliente(StatoCliente statoCliente) {
		this.statoCliente = statoCliente;
	}
	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public BigDecimal getCreditoUtilizzato() {
		return creditoUtilizzato;
	}
	public void setCreditoUtilizzato(BigDecimal creditoUtilizzato) {
		this.creditoUtilizzato = creditoUtilizzato;
	}
	

}
