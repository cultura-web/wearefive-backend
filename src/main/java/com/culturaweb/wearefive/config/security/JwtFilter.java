package com.culturaweb.wearefive.config.security;

import com.culturaweb.wearefive.service.DetallesUsuarioService;
import com.culturaweb.wearefive.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final DetallesUsuarioService detallesUsuarioService;

    public JwtFilter(JwtUtil jwtUtil, DetallesUsuarioService detallesUsuarioService) {
        this.jwtUtil = jwtUtil;
        this.detallesUsuarioService = detallesUsuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.getUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = detallesUsuarioService.loadUserByUsername(username);

                if (jwtUtil.validate(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

}
