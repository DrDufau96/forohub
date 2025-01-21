package com.aluracursos.forohub.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private final Key secretKey;


    public JwtService(@Value("${api.security.secret}") String secretBase64) {
        byte[] decodedKey = Base64.getDecoder().decode(secretBase64);
        this.secretKey = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS256.getJcaName());
        logger.info(" JWT y clave iniciados correctamente. ");
    }


    public String generateToken(String username) {
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) //
                .signWith(secretKey)
                .compact();
        logger.debug("Token generado para el usuario '{}': {}", username, token);
        return token;
    }



    public String getSubject(String token) {
        try {
            String subject = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            logger.debug("Token validado correctamente. Subject: {}", subject);
            return subject;
        } catch (JwtException e) {
            logger.error("Error al validar el token: {}", e.getMessage());
            throw e;
        }
    }
}



