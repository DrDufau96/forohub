package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.model.Usuario;
import com.aluracursos.forohub.infra.security.DatosJWTToken;
import com.aluracursos.forohub.infra.security.TokenService;
import com.aluracursos.forohub.service.JwtService; // Asegúrate de importar esta clase
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")

public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public DatosJWTToken autenticarUsuario(@RequestBody Usuario datosAutenticacion) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacion.getLogin(),
                datosAutenticacion.getClave()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        var usuario = (Usuario) authentication.getPrincipal();

        var tokenJWT = tokenService.generarToken(usuario);
        return new DatosJWTToken(tokenJWT);
    }

    @PostMapping("/token")
    public String generateToken() {
        return jwtService.generateToken("Alejandro.Dufau");
    }
}



