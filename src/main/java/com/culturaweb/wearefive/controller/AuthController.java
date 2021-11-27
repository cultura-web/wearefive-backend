package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.AuthenticationRequestDTO;
import com.culturaweb.wearefive.dto.AuthenticationResponseDTO;
import com.culturaweb.wearefive.service.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        String jwt = this.authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }
}
