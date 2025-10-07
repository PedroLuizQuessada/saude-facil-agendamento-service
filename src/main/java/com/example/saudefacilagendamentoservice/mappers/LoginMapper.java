package com.example.saudefacilagendamentoservice.mappers;

import com.example.saudefacilagendamentoservice.entities.Usuario;
import dtos.responses.LoginResponse;

public class LoginMapper {

    private LoginMapper(){}

    public static LoginResponse toResponse(Usuario user) {
        return new LoginResponse(user.getEmail(), user.getSenha(), user.getTipo());
    }

}
