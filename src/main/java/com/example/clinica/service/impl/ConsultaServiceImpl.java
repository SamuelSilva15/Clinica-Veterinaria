package com.example.clinica.service.impl;

import com.example.clinica.model.Consulta;
import com.example.clinica.service.ConsultaService;
import com.example.clinica.repository.ConsultaRepository;
import org.springframework.data.domain.*;

public class ConsultaServiceImpl implements ConsultaService {

    private ConsultaRepository consultaRepository;
    @Override
    public Consulta save(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

}
