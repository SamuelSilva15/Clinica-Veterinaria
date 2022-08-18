package com.example.clinica.login.dto;

public class TokenDto {

    private String token;
    private String tipo;

    public TokenDto(String token, String bearer) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
