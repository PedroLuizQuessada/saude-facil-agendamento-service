package com.example.saudefacilagendamentoservice.dtos.responses;

import com.example.saudefacilagendamentoservice.enums.TipoUsuarioEnum;

public record UsuarioResponse(Long id, String nome, String email, TipoUsuarioEnum tipo) {
}
