package br.com.supernova.anacarolpet.config.security;

import br.com.supernova.anacarolpet.Repository.UsuarioRepository;
import br.com.supernova.anacarolpet.domain.usuario.service.JWTToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTToken service;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var jwtToken = recoverToken(request);
        if(jwtToken != null) {
            var login = service.getSubject(jwtToken);
            var usuario = repository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        if(authorization != null) return authorization.split(" ")[1];

        return null;
    }
}
