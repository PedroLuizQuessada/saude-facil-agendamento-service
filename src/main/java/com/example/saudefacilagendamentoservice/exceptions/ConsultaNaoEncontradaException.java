package com.example.saudefacilagendamentoservice.exceptions;

import com.example.saudefacilagendamentoservice.exceptions.treateds.NotFoundException;

public class ConsultaNaoEncontradaException extends NotFoundException {
    public ConsultaNaoEncontradaException(String message) {
        super(message);
    }
}
