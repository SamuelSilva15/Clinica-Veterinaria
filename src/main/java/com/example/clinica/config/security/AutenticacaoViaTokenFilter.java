package com.example.clinica.config.security;

import com.example.clinica.veterinario.model.Veterinario;
import com.example.clinica.veterinario.repository.VeterinarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private VeterinarioRepository repository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, VeterinarioRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);

        if(valido){
            autenticarVeterianaio(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarVeterianaio(String token) {
        Long idVeterinario = tokenService.getIdVeterinario(token);
        Veterinario veterinario = repository.findById(idVeterinario).get();
        UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(veterinario, null, veterinario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(autenticacao);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if( token == null || token.isEmpty() || token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
