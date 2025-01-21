package com.aluracursos.forohub.infra;

import com.aluracursos.forohub.repository.UsuarioRepository;
import com.aluracursos.forohub.infra.security.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    private final TokenService tokenService;

    private final UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.debug("Token inválido.");
                filterChain.doFilter(request, response);
                return;
            }

            String token = authHeader.replace("Bearer ", "").trim();
            if (token.isEmpty()) {
                logger.debug("Token vacío. ");
                filterChain.doFilter(request, response);
                return;
            }

            logger.debug("Token recibido en el filtro: {}", token);

            String loginUsuario = tokenService.getSubject(token);
            logger.debug("Token válido. Login: {}", loginUsuario);

            if (loginUsuario != null) {
                var usuario = usuarioRepository.findByLogin(loginUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado. "));

                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            logger.error("Error al validar el token: {}", e.getMessage(), e);
        }

        filterChain.doFilter(request, response);
    }
}


