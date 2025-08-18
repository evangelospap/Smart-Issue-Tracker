package com.smartissuetracker.backend.config;

import java.util.Arrays;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final List<String> PUBLIC_URIS = Arrays.asList("/ping", "/**.html", "/**.js", "/**.css", "/favicon.ico");

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // disable CSRF protection for simplicity, adjust as needed
            .csrf(crsf -> crsf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(createRequestMatchers()).permitAll() // no auth needed
                // .anyRequest().authenticated()             // everything else requires Clerk token
            )
            // enables JWT auth
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}))
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
