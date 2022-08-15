package com.example.clinica.consulta.controller;

import com.example.clinica.consulta.controller.model.Consulta;
import com.example.clinica.consulta.controller.service.ConsultaService;
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

}
