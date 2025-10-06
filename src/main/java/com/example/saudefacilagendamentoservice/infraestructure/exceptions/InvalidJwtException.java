package com.example.saudefacilagendamentoservice.infraestructure.exceptions;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException() {
        super("Token de acesso inválido");
    }
}
