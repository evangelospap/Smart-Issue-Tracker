package com.smartissuetracker.backend.security.authentication;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class ClerkJwtFilter extends OncePerRequestFilter {

    private final JwtVerifier verifier;
    private final JwtService jwtService;

    public ClerkJwtFilter(JwtVerifier verifier, JwtService jwtService) {
        this.verifier = verifier;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = this.jwtService.getRawToken(request);
        if (jwt != null) {
            // Verify the JWT
            try {
                DecodedJWT decoded = verifier.verify(jwt);
                String userId = decoded.getClaim("sub").asString();


                // Attach userId to request context (optional)
                request.setAttribute("userId", userId);

            } catch (JWTVerificationException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid Clerk token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
