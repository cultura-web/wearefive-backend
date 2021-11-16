package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.domain.Usuario;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public abstract class DummyUserDAO implements IUserDAO {

    @Override
    public Usuario findByUsername(String username) throws UsernameNotFoundException {
        if ("prueba".equals(username)) {
            return new Usuario(0, "prueba", "$2a$10$1AkCYfq/LV3j4dg669wN2OuorEjSJM3vAWC1jSOdgiCOcBfDNcg92");
        } else throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
