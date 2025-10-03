package com.example.saudefacilagendamentoservice.dtos;

import com.example.saudefacilagendamentoservice.enums.TipoUsuarioEnum;

public record UsuarioDto(Long id, String nome, String email, String senha, TipoUsuarioEnum tipo) {
}
