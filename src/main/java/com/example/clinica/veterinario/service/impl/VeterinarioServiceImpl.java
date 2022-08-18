package com.example.clinica.veterinario.service.impl;

import com.example.clinica.veterinario.model.Veterinario;
import com.example.clinica.veterinario.repository.VeterinarioRepository;
import com.example.clinica.veterinario.service.VeterinarioService;
import com.example.clinica.veterinario.exception.VeterinarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    private VeterinarioRepository veterinarioRepository;

    @Autowired
    public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public Veterinario save(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    public List<Veterinario> findAll() {
        return veterinarioRepository.findAll();
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

    @Override
    public Veterinario atualizaDados(Long id, Veterinario veterinario) {
        Veterinario entity = findById(id);

        //fazer copia
        entity.setNome(veterinario.getNome());
        entity.setDataNascimento(veterinario.getDataNascimento());
        entity.setTelefone(veterinario.getTelefone());
        entity.setEmail(veterinario.getEmail());
        entity.setSenha(veterinario.getSenha());

        return veterinarioRepository.save(entity);
    }
}
