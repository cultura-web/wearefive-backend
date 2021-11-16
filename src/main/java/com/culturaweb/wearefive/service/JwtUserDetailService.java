package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.model.domain.Usuario;
import com.culturaweb.wearefive.model.dto.UserDTO;
import com.culturaweb.wearefive.repository.IUserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private IUserDAO userRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = userRepo.findByUsername(s);
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }

    public UserDTO registerUser(UserDTO newUserDTO) {
        Usuario newUsuario = mapper.map(newUserDTO, Usuario.class);
        newUsuario.setPassword(bcryptEncoder.encode(newUserDTO.getPassword()));

        userRepo.save(newUsuario);

        return newUserDTO;
    }
}
