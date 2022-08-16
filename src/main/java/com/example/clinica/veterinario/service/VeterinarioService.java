package com.example.clinica.veterinario.service;

import com.example.clinica.veterinario.model.Veterinario;

import java.util.Optional;

public interface VeterinarioService {

    Veterinario save(Veterinario veterinario);
    Veterinario findById(Long id);
    void deleteById(Long id);
    Veterinario AtualizaDados(Veterinario veterinario);


}
