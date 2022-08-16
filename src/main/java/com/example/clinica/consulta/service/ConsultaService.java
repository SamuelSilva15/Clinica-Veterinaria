package com.example.clinica.consulta.service;

import com.example.clinica.consulta.model.Consulta;


public interface ConsultaService {
    Consulta save(Consulta consulta);

    void deleteById(Long id);

    Consulta findById(Long id);
}
