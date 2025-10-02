package com.example.saudefacilagendamentoservice.exceptions.treateds;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
