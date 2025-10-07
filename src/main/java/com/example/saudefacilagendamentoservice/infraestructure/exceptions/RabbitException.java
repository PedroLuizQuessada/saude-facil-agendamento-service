package com.example.saudefacilagendamentoservice.infraestructure.exceptions;

public class RabbitException extends RuntimeException {
    public RabbitException(String message) {
        super(message);
    }
}
