package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.model.dto.JwtTokenResponseDTO;
import com.culturaweb.wearefive.security.JwtTokenUtil;
import com.culturaweb.wearefive.model.dto.UserDTO;
import com.culturaweb.wearefive.service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtUserDetailService userDetailsService;

    @PostMapping("/authenticate")
    public JwtTokenResponseDTO authenticate(@RequestBody UserDTO userRequest) throws Exception {
        authenticate(userRequest.getUsername(), userRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtTokenResponseDTO(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.registerUser(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

