package com.example.clinica.veterinario.exception;


import javax.persistence.NoResultException;

public class VeterinarioNotFoundException extends NoResultException {
    public VeterinarioNotFoundException(Long id) {
        super("Produto não encontrado: " + id);
    }
}
