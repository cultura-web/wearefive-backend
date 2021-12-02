package com.culturaweb.wearefive.config.security;

import com.culturaweb.wearefive.service.DetallesUsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DetallesUsuarioService detallesUsuarioService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(DetallesUsuarioService detallesUsuarioService, JwtFilter jwtFilter) {
        this.detallesUsuarioService = detallesUsuarioService;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detallesUsuarioService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .authorizeRequests().antMatchers("/auth/**/**").permitAll()
                .antMatchers("/ping").permitAll()
                .antMatchers("/api/qa").permitAll()
                .antMatchers("/api/products/list").permitAll()
                .antMatchers("/api/products/model").permitAll()
                .antMatchers("/api/products/model/{nombre}").permitAll()
                .antMatchers("/h2-console/**/**").permitAll()
                .anyRequest().authenticated().and().headers().frameOptions().sameOrigin()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
