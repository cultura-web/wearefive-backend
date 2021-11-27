package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.AuthenticationRequestDTO;
import com.culturaweb.wearefive.dto.AuthenticationResponseDTO;
import com.culturaweb.wearefive.dto.RegistroClienteDTO;
import com.culturaweb.wearefive.service.AuthServiceImpl;
import com.culturaweb.wearefive.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final UsuarioServiceImpl usuarioService;
    private final AuthServiceImpl authService;

    public AuthController(UsuarioServiceImpl usuarioService, AuthServiceImpl authService) {
        this.usuarioService = usuarioService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@Valid @RequestBody AuthenticationRequestDTO request) {
        String token = this.authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthenticationResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registrarCliente(@Valid @RequestBody RegistroClienteDTO request){
        String r = this.usuarioService.registrarCliente(request);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
}
