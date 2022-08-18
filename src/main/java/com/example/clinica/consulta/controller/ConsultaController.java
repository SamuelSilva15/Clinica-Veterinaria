package com.example.clinica.consulta.controller;

import com.example.clinica.consulta.model.Consulta;
import com.example.clinica.consulta.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/v1/atendimento")
@Api(value="Clinica Veterinária")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public Consulta save(@RequestBody @Valid Consulta consulta) {
        return consultaService.save(consulta);
    }

    @GetMapping
    public List<Consulta> findAll(){
        return consultaService.findAll();
    }

    @GetMapping("/{id}")
    public Consulta findById(@PathVariable Long id){
        return consultaService.findById(id);
    }

    @PutMapping("/{id}")
    public Consulta atualizaConsulta(@PathVariable Long id, @RequestBody @Valid Consulta consulta){
        return consultaService.atualizaDados(id, consulta);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        consultaService.deleteById(id);
    }



}
