package com.example.saudefacilagendamentoservice.controllers;

import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import com.example.saudefacilagendamentoservice.dtos.requests.CriarUsuarioRequest;
import com.example.saudefacilagendamentoservice.gateways.UsuarioGateway;
import com.example.saudefacilagendamentoservice.usecases.CriarUsuarioUseCase;

public class UsuarioController {

    private final UsuarioDataSource usuarioDataSource;

    public UsuarioController(UsuarioDataSource usuarioDataSource) {
        this.usuarioDataSource = usuarioDataSource;
    }

    public void criarUsuario(CriarUsuarioRequest request) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        CriarUsuarioUseCase useCase = new CriarUsuarioUseCase(usuarioGateway);
        useCase.execute(request);
    }

}
