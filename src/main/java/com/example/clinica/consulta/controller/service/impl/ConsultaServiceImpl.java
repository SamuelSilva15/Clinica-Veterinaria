package com.example.clinica.consulta.controller.service.impl;

import com.example.clinica.consulta.controller.model.Consulta;
import com.example.clinica.consulta.controller.service.ConsultaService;
import com.example.clinica.consulta.controller.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public Consulta save(Consulta consulta) {
        consultaRepository.save(consulta);
        return consulta;
    }


}
