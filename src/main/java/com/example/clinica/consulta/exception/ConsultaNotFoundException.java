package com.example.clinica.consulta.exception;

import javax.persistence.NoResultException;

public class ConsultaNotFoundException extends NoResultException {
    public ConsultaNotFoundException(Long id) {
        super("Produto não encontrado: " + id);
    }
}
