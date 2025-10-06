package com.example.saudefacilagendamentoservice.usecases;

import com.example.saudefacilagendamentoservice.entities.Usuario;
import com.example.saudefacilagendamentoservice.gateways.UsuarioGateway;

public class LoginUseCase {

    private final UsuarioGateway usuarioGateway;

    public LoginUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario execute(String email) {
        return usuarioGateway.encontrarUsuarioPorEmail(email);
    }
}
