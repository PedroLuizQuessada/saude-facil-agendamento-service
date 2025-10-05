package com.example.saudefacilagendamentoservice.exceptions;

import com.example.saudefacilagendamentoservice.exceptions.treateds.NotFoundException;

public class UsuarioNaoEncontradoException extends NotFoundException {
    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
}
