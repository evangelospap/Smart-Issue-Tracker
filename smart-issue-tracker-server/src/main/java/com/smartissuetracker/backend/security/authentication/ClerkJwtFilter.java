// package com.smartissuetracker.backend.security.authentication;

// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.GenericFilterBean;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import com.auth0.jwt.interfaces.DecodedJWT;
// import com.auth0.jwt.exceptions.JWTVerificationException;
// @Component
// public class ClerkJwtFilter extends GenericFilterBean {

//     private final JwtVerifier verifier;
//     private final JwtService jwtService;

//     public ClerkJwtFilter(JwtVerifier verifier, JwtService jwtService) {
//         this.verifier = verifier;
//         this.jwtService = jwtService;
//     }

//     @Override
//     public void doFilter(
//             ServletRequest servletRequest,
//             ServletResponse servletResponse,
//             FilterChain filterChain)
//             throws ServletException, IOException {

//         HttpServletRequest request = (HttpServletRequest) servletRequest;
//         HttpServletResponse response = (HttpServletResponse) servletResponse;

//         String jwt = this.jwtService.getRawToken(request);
//         if (jwt != null) {
//             // Verify the JWT
//             try {
//                 DecodedJWT decoded = verifier.verify(jwt);
//                 String userId = decoded.getClaim("sub").asString();

//                 // Attach userId to request context (optional)
//                 request.setAttribute("userId", userId);

//             } catch (JWTVerificationException e) {
//                 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                 response.getWriter().write("Invalid Clerk token");
//                 return;
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }
