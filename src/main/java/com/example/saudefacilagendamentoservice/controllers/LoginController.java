package com.example.saudefacilagendamentoservice.controllers;

import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import com.example.saudefacilagendamentoservice.entities.Usuario;
import com.example.saudefacilagendamentoservice.gateways.UsuarioGateway;
import com.example.saudefacilagendamentoservice.mappers.LoginMapper;
import com.example.saudefacilagendamentoservice.usecases.LoginUseCase;
import dtos.responses.LoginResponse;

public class LoginController {

    private final UsuarioDataSource usuarioDataSource;

    public LoginController(UsuarioDataSource usuarioDataSource) {
        this.usuarioDataSource = usuarioDataSource;
    }

    public LoginResponse login(String login) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(this.usuarioDataSource);
        LoginUseCase loginUseCase = new LoginUseCase(usuarioGateway);
        Usuario user = loginUseCase.execute(login);
        return LoginMapper.toResponse(user);
    }

}
