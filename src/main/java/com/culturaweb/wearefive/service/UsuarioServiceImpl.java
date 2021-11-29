package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.RegistroClienteDTO;
import com.culturaweb.wearefive.exceptions.CorreoYaExisteException;
import com.culturaweb.wearefive.exceptions.UsuarioYaExisteException;
import com.culturaweb.wearefive.model.Cliente;
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

    public String registrarCliente(RegistroClienteDTO r) {
        if(this.usuarioRepository.existsByUsername(r.getUsername()))
            throw new UsuarioYaExisteException();
        if(this.usuarioRepository.existsByCorreo(r.getCorreo()))
            throw new CorreoYaExisteException();

        Usuario u = new Usuario(r.getNombres(),r.getApellidos(),r.getUsername(),r.getContrasena(),r.getCorreo());
        u.setCliente(new Cliente(r.getDireccion(),r.getCelular(),u));

        this.usuarioRepository.save(u);
        return "Registro exitoso";
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
