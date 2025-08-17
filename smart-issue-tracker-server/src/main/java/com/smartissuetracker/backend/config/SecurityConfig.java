package com.smartissuetracker.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll() // no auth needed
                .anyRequest().authenticated()             // everything else requires Clerk token
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt()); // enables JWT auth

        return http.build();
    }
}
