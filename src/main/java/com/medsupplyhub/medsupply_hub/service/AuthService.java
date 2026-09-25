package com.medsupplyhub.medsupply_hub.service;

import com.medsupplyhub.medsupply_hub.dto.request.utente.LoginRequestDto;
import com.medsupplyhub.medsupply_hub.dto.response.utente.LoginResponseDto;

public interface AuthService {
public LoginResponseDto login(LoginRequestDto logindto);
}
