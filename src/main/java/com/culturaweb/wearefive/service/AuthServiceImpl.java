package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.model.Usuario;
import com.culturaweb.wearefive.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    private final UsuarioServiceImpl usuarioService;
    private final DetallesUsuarioService detallesUsuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UsuarioServiceImpl usuarioService, DetallesUsuarioService detallesUsuarioService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.detallesUsuarioService = detallesUsuarioService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String login(String username, String password){
        Usuario usuario = this.usuarioService.buscarPorUsernameYPassword(username,password);
        if(usuario == null)
            throw new BadCredentialsException("Datos de acceso incorrectos");

        UserDetails userDetails = detallesUsuarioService.loadUserByUsername(username);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return jwtUtil.generateToken(userDetails);
    }
}
