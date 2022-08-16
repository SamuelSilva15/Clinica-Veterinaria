package com.example.clinica.consulta.service.impl;

import com.example.clinica.consulta.exception.ConsultaNotFoundException;
import com.example.clinica.consulta.model.Consulta;
import com.example.clinica.consulta.service.ConsultaService;
import com.example.clinica.consulta.repository.ConsultaRepository;
import com.example.clinica.veterinario.exception.VeterinarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public Consulta save(Consulta consulta) {
        return consultaRepository.save(consulta);
    }


    @Override
    public Consulta findById(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        if(consultaRepository.existsById(id)){
            consultaRepository.deleteById(id);
        }
    }

    @Override
    public Consulta atualizaDados(Consulta consulta) {
        return consultaRepository.save(consulta);
    }


}
