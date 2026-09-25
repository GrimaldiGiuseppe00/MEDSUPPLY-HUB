package com.medsupplyhub.medsupply_hub.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medsupplyhub.medsupply_hub.dto.request.utente.LoginRequestDto;
import com.medsupplyhub.medsupply_hub.dto.response.utente.LoginResponseDto;
import com.medsupplyhub.medsupply_hub.service.AuthService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

	private final AuthService authService;

	public AuthRestController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> generaTokenJwt(@RequestBody @Valid LoginRequestDto loginRequestDto) {
		LoginResponseDto loginResponseDto= authService.login(loginRequestDto);
			 return new ResponseEntity<>(loginResponseDto,HttpStatus.OK);
	}
	@GetMapping("/verifica")
	public String vericaRequestConToken() {
		return "token valido";
}
	
		
	
}
