package com.example.clinica.veterinario.controller;

import com.example.clinica.veterinario.model.Veterinario;
import com.example.clinica.veterinario.service.VeterinarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/v1/veterinarios")
@Api(value="Clinica Veterinária")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @PostMapping
    public Veterinario save(@RequestBody @Valid Veterinario veterinario){
        return veterinarioService.save(veterinario);
    }

    @GetMapping
    public List<Veterinario> findAll(){
        return veterinarioService.findAll();
    }

    @GetMapping("/{id}")
    public Veterinario findById(@PathVariable Long id){
        return veterinarioService.findById(id);
    }

    @PutMapping("/{id}")
    public Veterinario atualizaDadosPorId(@PathVariable Long id, @RequestBody @Valid Veterinario veterinario){
        return veterinarioService.atualizaDados(id, veterinario);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        veterinarioService.deleteById(id);
    }
}
