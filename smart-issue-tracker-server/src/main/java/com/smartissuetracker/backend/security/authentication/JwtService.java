// package com.smartissuetracker.backend.security.authentication;

// import org.springframework.stereotype.Service;

// import jakarta.servlet.http.HttpServletRequest;

// @Service
// public class JwtService {
    
//     public String getRawToken(HttpServletRequest request) {
//         String authHeader = request.getHeader("Authorization");
//         if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             return authHeader.substring(7);
//         }
//         return null;
//     }   
// }
