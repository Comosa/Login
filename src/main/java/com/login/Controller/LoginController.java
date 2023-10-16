package com.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.Domain.Usuarios.Autenticacion;
import com.login.Domain.Usuarios.usuario;
import com.login.infra.security.DatosJWTToken;
import com.login.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/forms")
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	@PostMapping
	public ResponseEntity Login(@RequestBody @Valid Autenticacion autenticacion) {
		Authentication Authtoken= new UsernamePasswordAuthenticationToken(autenticacion.login(), autenticacion.clave());
	var Usuarioautenticacion =	authenticationManager.authenticate(Authtoken);
	var JWTtoken = tokenService.GenerarToken((usuario) Usuarioautenticacion.getPrincipal());
		return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
	}
	@GetMapping
	public String Saludo() {
		return"Hola mundo";
	}
}
