package com.example.saudefacilagendamentoservice.exceptions;

import com.example.saudefacilagendamentoservice.exceptions.treateds.BadRequestException;

public class BadArgumentException extends BadRequestException {
    public BadArgumentException(String message) {
        super(message);
    }
}
