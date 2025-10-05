package com.example.saudefacilagendamentoservice.exceptions;

import com.example.saudefacilagendamentoservice.exceptions.treateds.BadRequestException;

public class UsuarioOcupadoException extends BadRequestException {
    public UsuarioOcupadoException(String message) {
        super(message);
    }
}
