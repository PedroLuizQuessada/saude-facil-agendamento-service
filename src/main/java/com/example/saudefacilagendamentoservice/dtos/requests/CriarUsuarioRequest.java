package com.example.saudefacilagendamentoservice.dtos.requests;

import com.example.saudefacilagendamentoservice.enums.TipoUsuarioEnum;

public record CriarUsuarioRequest(String nome, String email, String senha, TipoUsuarioEnum tipo) {
}
