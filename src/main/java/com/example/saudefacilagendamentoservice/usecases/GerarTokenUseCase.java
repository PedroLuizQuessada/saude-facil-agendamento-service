package com.example.saudefacilagendamentoservice.usecases;

import com.example.saudefacilagendamentoservice.entities.Token;
import com.example.saudefacilagendamentoservice.gateways.TokenGateway;

public class GerarTokenUseCase {

    private final TokenGateway tokenGateway;

    public GerarTokenUseCase(TokenGateway tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    public Token execute(String tipoUsuario, String login) {
        return tokenGateway.gerarToken(tipoUsuario, login);
    }
}
