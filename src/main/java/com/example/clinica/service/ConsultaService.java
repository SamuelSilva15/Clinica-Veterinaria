package com.example.clinica.service;

import com.example.clinica.model.Consulta;
import org.springframework.stereotype.Service;


@Service
public interface ConsultaService {
    Consulta save(Consulta consulta);


}
