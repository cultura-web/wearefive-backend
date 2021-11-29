package com.culturaweb.wearefive.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class JwtUtil implements IJwtUtil{

    private String key = "d0784c6b1785dcd474688d46b1fe99792ff66f6b56bebf26dda0c08516bac22e";

    @Override
    public String generateToken(UserDetails details) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return Jwts.builder()
                .setSubject(details.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    @Override
    public boolean validate(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(getUsername(token)) && !isExpired(token);
    }

    @Override
    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        JwtParser jwtParser = Jwts.parser().setSigningKey(key);
        return jwtParser.parseClaimsJws(token).getBody();
    }
}
