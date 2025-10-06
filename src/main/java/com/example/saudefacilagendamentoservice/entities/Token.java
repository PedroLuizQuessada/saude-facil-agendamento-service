package com.example.saudefacilagendamentoservice.entities;

import com.example.saudefacilagendamentoservice.exceptions.TokenException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Token {

    private final String token;

    public Token(String token) {
        validateToken(token);

        this.token = token;
    }

    private void validateToken(String token) {
        if (Objects.isNull(token) || token.isEmpty())
            throw new TokenException("O token deve possuir um valor.");
    }
}
