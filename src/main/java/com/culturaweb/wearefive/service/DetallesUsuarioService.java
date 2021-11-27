package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.model.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetallesUsuarioService implements UserDetailsService {

    private final UsuarioServiceImpl usuarioService;

    public DetallesUsuarioService(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioService.findByUsername(username);
        String role = usuarioService.getUserRole(user);

        return User.builder()
                .username(user.getUsername())
                .password("{noop}" + user.getContrasena())
                .roles(role)
                .build();
    }
}
