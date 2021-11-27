package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByUsernameEqualsAndContrasenaEquals(String username, String contrasena);
    boolean existsByUsername(String username);
    boolean existsByCorreo(String correo);
    Usuario findByUsernameEquals(String username);


}
