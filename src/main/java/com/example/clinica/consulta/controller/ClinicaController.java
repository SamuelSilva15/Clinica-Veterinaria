package com.example.clinica.consulta.controller;

import com.example.clinica.consulta.model.Consulta;
import com.example.clinica.consulta.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/v1/atendimento")
@Api(value="Clinica Veterinária")
public class ClinicaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public Consulta save(@RequestBody @Valid Consulta consulta) {
        return consultaService.save(consulta);
    }

    @GetMapping("/{id}")
    public Consulta findById(@PathVariable Long id){
        return consultaService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        consultaService.deleteById(id);
    }



}
