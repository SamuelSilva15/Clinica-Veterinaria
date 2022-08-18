package com.example.clinica.config.security;

import com.example.clinica.veterinario.model.Veterinario;
import com.example.clinica.veterinario.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private VeterinarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Veterinario> veterinario = repository.findByEmail(username);
        if (veterinario.isPresent()) {
            return veterinario.get();
        }

        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
