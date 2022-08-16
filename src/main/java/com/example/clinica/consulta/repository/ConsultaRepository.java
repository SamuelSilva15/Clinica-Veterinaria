package com.example.clinica.consulta.repository;

import com.example.clinica.consulta.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
