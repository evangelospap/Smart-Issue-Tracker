package com.smartissuetracker.backend.security.config;

import java.util.Arrays;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.smartissuetracker.backend.security.authentication.ClerkJwtFilter;
import com.smartissuetracker.backend.security.authentication.JwtVerifier;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final List<String> PUBLIC_URIS = Arrays.asList("/ping", "/**.html", "/**.js", "/**.css", "/favicon.ico");
    private final JwtVerifier jwtVerifier;

    public SecurityConfig(JwtVerifier jwtVerifier) {
        this.jwtVerifier = jwtVerifier;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        ClerkJwtFilter clerkFilter = new ClerkJwtFilter(jwtVerifier);
        http
            // disable CSRF protection for simplicity, adjust as needed
            .csrf(crsf -> crsf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(createRequestMatchers()).permitAll() // no auth needed
                .anyRequest().authenticated()             // everything else requires Clerk token
            )
            // use oauth via filter authentication
            .addFilterBefore(clerkFilter, UsernamePasswordAuthenticationFilter.class)
            // enables JWT o2auth
            //.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}))
            ;
            

        return http.build();

    }
    /**
     * Creates an array of RequestMatcher objects for the public URIs.
     *
     * @return an array of RequestMatcher objects
     */
    private RequestMatcher[] createRequestMatchers() {
        return PUBLIC_URIS.stream()
                .map(AntPathRequestMatcher::new)
                .toArray(RequestMatcher[]::new);
    }
}
