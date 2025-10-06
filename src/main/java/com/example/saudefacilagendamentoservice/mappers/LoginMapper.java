package com.example.saudefacilagendamentoservice.mappers;

import com.example.saudefacilagendamentoservice.dtos.responses.LoginResponse;
import com.example.saudefacilagendamentoservice.entities.Usuario;

public class LoginMapper {

    private LoginMapper(){}

    public static LoginResponse toResponse(Usuario user) {
        return new LoginResponse(user.getEmail(), user.getSenha(), user.getTipo());
    }

}
