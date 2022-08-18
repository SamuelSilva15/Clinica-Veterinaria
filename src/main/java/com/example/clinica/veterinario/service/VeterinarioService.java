package com.example.clinica.veterinario.service;

import com.example.clinica.veterinario.model.Veterinario;

import java.util.List;
import java.util.Optional;

public interface VeterinarioService {

    Veterinario save(Veterinario veterinario);
    List<Veterinario> findAll();
    Veterinario findById(Long id);
    void deleteById(Long id);
    Veterinario atualizaDados(Long id, Veterinario veterinario);
}
