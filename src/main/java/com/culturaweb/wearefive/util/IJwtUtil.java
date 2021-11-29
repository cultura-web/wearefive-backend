package com.culturaweb.wearefive.util;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtUtil {
    String generateToken(UserDetails details);

    boolean validate(String token, UserDetails userDetails);

    boolean isExpired(String token);

    String getUsername(String token);
}
