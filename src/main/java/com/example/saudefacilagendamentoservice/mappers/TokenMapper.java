package com.example.saudefacilagendamentoservice.mappers;

import com.example.saudefacilagendamentoservice.dtos.responses.TokenResponse;
import com.example.saudefacilagendamentoservice.entities.Token;

public class TokenMapper {

    private TokenMapper(){}

    public static TokenResponse toResponse(Token token) {
        return new TokenResponse(token.getToken());
    }

}
