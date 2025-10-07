package com.example.saudefacilagendamentoservice.mappers;

import com.example.saudefacilagendamentoservice.entities.Token;
import dtos.responses.TokenResponse;

public class TokenMapper {

    private TokenMapper(){}

    public static TokenResponse toResponse(Token token) {
        return new TokenResponse(token.getToken());
    }

}
