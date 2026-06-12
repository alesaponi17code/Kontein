package com.proyects.kontein.infrastructure.in.security;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
    
    private final SecretKey secretKey;
    private final long expiration;

    public JwtService(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.expiration}") long expiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    public String generarToken(String correo, String rol) {
        return Jwts.builder()
            .subject(correo)
            .claim("rol", rol)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(secretKey)
            .compact();
    }

    public String extraerCorreo(String token) {
        return extraerClaims(token).getSubject();
    }

    public boolean esValido(String token, String correo) {
        String correoToken = extraerCorreo(token);
        return correoToken.equals(correo) && !haExpirado(token);
    }

    //metodos privados

    private Claims extraerClaims(String token) {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private boolean haExpirado(String token) {
        return extraerClaims(token).getExpiration().before(new Date());
    }
}
