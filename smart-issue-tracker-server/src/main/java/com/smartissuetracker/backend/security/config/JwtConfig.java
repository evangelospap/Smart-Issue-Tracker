
package com.smartissuetracker.backend.security.config;

// import com.smartissuetracker.backend.security.authentication.JwtVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.interfaces.RSAPublicKey;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {

    @Value("${clerk.public-key}")
    private String publicKeyPem;

    @Value("${clerk.issuer-uri}")
    private String issuerUri;
    @Value("${clerk.jwks-uri}")
    private String jwksUri;

    // @Bean
    // public JwtVerifier jwtVerifier() throws Exception {
    //     RSAPublicKey publicKey = parsePublicKey(publicKeyPem);
    //     return new JwtVerifier(publicKey, issuerUri);
    // }

    // private RSAPublicKey parsePublicKey(String pem) throws Exception {
    //     String cleanPem = pem
    //         .replace("-----BEGIN PUBLIC KEY-----", "")
    //         .replace("-----END PUBLIC KEY-----", "")
    //         .trim();

    //     // Remove line breaks safely
    //     cleanPem = cleanPem.replaceAll("\\r?\\n", "");

    //     byte[] decoded = Base64.getDecoder().decode(cleanPem);
    //     X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
    //     return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
    // }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Build a decoder from Clerk’s JWKS endpoint
        return NimbusJwtDecoder.withJwkSetUri(this.jwksUri).jwsAlgorithm(SignatureAlgorithm.RS256).build();
    }
}