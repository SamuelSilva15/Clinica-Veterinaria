package com.example.clinica.controller;

import com.example.clinica.model.Consulta;
import com.example.clinica.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/clinica/atendimento")
@Api(value="Clinica Veterinária")
public class ClinicaController {

    @Autowired(required = false)
    private ConsultaService consultaService;

    @PostMapping
    public Consulta salvaConsulta(@RequestBody @Valid Consulta consulta) {
        return consultaService.save(consulta);
    }


}
