package com.smartissuetracker.backend.security.authentication;

import java.security.interfaces.RSAPublicKey;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JwtVerifier {

    private final JWTVerifier verifier;
        public JwtVerifier(RSAPublicKey publicKey, String issuerUri) {
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        this.verifier = JWT.require(algorithm)
            .withIssuer(issuerUri)
            .build();
    }

    public DecodedJWT verify(String token) {
        return verifier.verify(token);
    }

}