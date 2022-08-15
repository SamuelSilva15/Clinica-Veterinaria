package com.example.clinica.consulta.controller.repository;

import com.example.clinica.consulta.controller.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
