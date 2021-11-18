package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.domain.Usuario;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository @Primary

public interface IUserDAO extends CrudRepository<Usuario, Long> {
    Usuario findByUsername(String username) throws UsernameNotFoundException;
}
