package com.example.clinica.veterinario.service.impl;

import com.example.clinica.veterinario.model.Veterinario;
import com.example.clinica.veterinario.repository.VeterinarioRepository;
import com.example.clinica.veterinario.service.VeterinarioService;
import com.example.clinica.veterinario.exception.VeterinarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Override
    public Veterinario save(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }
    @Override
    public Veterinario findById(Long id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new VeterinarioNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        if(veterinarioRepository.existsById(id)){
        veterinarioRepository.deleteById(id);
        }
    }
}
