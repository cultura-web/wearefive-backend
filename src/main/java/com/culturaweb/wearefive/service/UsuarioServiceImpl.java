package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.model.Usuario;
import com.culturaweb.wearefive.repository.IUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByUsername(String username){
        return this.usuarioRepository.findByUsernameEquals(username);
    }

    public Usuario buscarPorUsernameYPassword(String username, String password){
        return this.usuarioRepository.findByUsernameEqualsAndContrasenaEquals(username,password);
    }

    public String getUserRole(Usuario usuario){
        if (usuario.getAdmin() != null) {
            return "ADMIN";
        } else if (usuario.getCliente() != null) {
            return "CLIENTE";
        } else {
            return null;
        }
    }
}
