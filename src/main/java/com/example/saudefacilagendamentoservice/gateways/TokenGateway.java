package com.example.saudefacilagendamentoservice.gateways;

import com.example.saudefacilagendamentoservice.datasources.TokenDataSource;
import com.example.saudefacilagendamentoservice.dtos.TokenDto;
import com.example.saudefacilagendamentoservice.entities.Token;

public class TokenGateway {

    private final TokenDataSource tokenDataSource;

    public TokenGateway(TokenDataSource tokenDataSource) {
        this.tokenDataSource = tokenDataSource;
    }

    public Token gerarToken(String tipoUsuario, String login) {
        TokenDto tokenDto = tokenDataSource.gerarToken(tipoUsuario, login);
        return new Token(tokenDto.token());
    }

    public String getEmail(String token) {
        return tokenDataSource.getEmail(token);
    }
}
