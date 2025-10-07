package com.example.saudefacilagendamentoservice.controllers;

import com.example.saudefacilagendamentoservice.datasources.TokenDataSource;
import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import com.example.saudefacilagendamentoservice.entities.Token;
import com.example.saudefacilagendamentoservice.gateways.TokenGateway;
import com.example.saudefacilagendamentoservice.gateways.UsuarioGateway;
import com.example.saudefacilagendamentoservice.mappers.TokenMapper;
import com.example.saudefacilagendamentoservice.usecases.CriarUsuarioUseCase;
import com.example.saudefacilagendamentoservice.usecases.GerarTokenUseCase;
import dtos.requests.CriarUsuarioRequest;
import dtos.responses.TokenResponse;

public class UsuarioController {

    private final TokenDataSource tokenDataSource;
    private final UsuarioDataSource usuarioDataSource;

    public UsuarioController(TokenDataSource tokenDataSource, UsuarioDataSource usuarioDataSource) {
        this.tokenDataSource = tokenDataSource;
        this.usuarioDataSource = usuarioDataSource;
    }

    public TokenResponse gerarToken(String tipoUsuario, String login) {
        TokenGateway tokenGateway = new TokenGateway(tokenDataSource);
        GerarTokenUseCase useCase = new GerarTokenUseCase(tokenGateway);
        Token token = useCase.execute(tipoUsuario, login);
        return TokenMapper.toResponse(token);
    }

    public void criarUsuario(CriarUsuarioRequest request) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        CriarUsuarioUseCase useCase = new CriarUsuarioUseCase(usuarioGateway);
        useCase.execute(request);
    }

}
