package com.medsupplyhub.medsupply_hub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.medsupplyhub.medsupply_hub.dto.request.utente.CreazioneUtenteDto;
import com.medsupplyhub.medsupply_hub.dto.response.utente.UtenteResponseDto;
import com.medsupplyhub.medsupply_hub.model.Utente;

@Mapper(componentModel = "spring")
public interface UtenteMapper {
	
	@Mapping(source = "nome",target = "nome")
	@Mapping(source = "cognome",target = "cognome")
	@Mapping(source = "email",target = "email")
	@Mapping(source = "ruoloUtente",target = "ruoloUtente")
	@Mapping(source = "telefono",target = "telefono")
	@Mapping(target = "cliente", ignore = true)
	Utente creazioneUtenteDtoToUtente(CreazioneUtenteDto dto);
	
	@Mapping(source = "nome",target = "nome")
	@Mapping(source = "cognome",target = "cognome")
	@Mapping(source = "email",target = "email")
	@Mapping(source = "ruoloUtente",target = "ruoloUtente")
	@Mapping(source = "telefono",target = "telefono")
	@Mapping(source="statoUtente",target = "statoUtente")
	UtenteResponseDto utenteToUtenteResponseDto(Utente utente);
	
}
