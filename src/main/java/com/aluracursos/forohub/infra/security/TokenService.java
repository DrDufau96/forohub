package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getLogin()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(SECRET_KEY).compact();
    }

    public String getSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY).build().parseClaimsJws(token)
                .getBody().getSubject();
    }
}



