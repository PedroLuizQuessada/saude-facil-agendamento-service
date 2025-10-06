package com.example.saudefacilagendamentoservice.infraestructure.exceptions;

public class BadJpaArgumentException extends RuntimeException {
    public BadJpaArgumentException(String message) {
        super(message);
    }
}
