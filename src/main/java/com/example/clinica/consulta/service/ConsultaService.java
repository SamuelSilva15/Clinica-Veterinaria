package com.example.clinica.consulta.service;

import com.example.clinica.consulta.model.Consulta;

import java.util.List;


public interface ConsultaService {
    Consulta save(Consulta consulta);

    void deleteById(Long id);

    Consulta atualizaDados(Long id, Consulta consulta);

    Consulta findById(Long id);

    List<Consulta> findAll();
}
