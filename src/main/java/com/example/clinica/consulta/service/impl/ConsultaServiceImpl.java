package com.example.clinica.consulta.service.impl;

import com.example.clinica.consulta.exception.ConsultaNotFoundException;
import com.example.clinica.consulta.model.Consulta;
import com.example.clinica.consulta.service.ConsultaService;
import com.example.clinica.consulta.repository.ConsultaRepository;
import com.example.clinica.veterinario.exception.VeterinarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;

    @Autowired
    public ConsultaServiceImpl(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public Consulta save(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Override
    public List<Consulta> findAll() {
        return consultaRepository.findAll();
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
    public Consulta atualizaDados(Long id, Consulta consulta) {
        Optional<Consulta> v = consultaRepository.findById(id);
        if (v.isPresent()) {
            Consulta entity = v.get();
            //fazer copia
            entity.setNomeCliente(consulta.getNomeCliente());
            entity.setData(consulta.getData());
            return consultaRepository.save(entity);
        }

        throw new VeterinarioNotFoundException(id);
    }
}
