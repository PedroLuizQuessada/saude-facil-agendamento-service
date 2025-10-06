package com.example.saudefacilagendamentoservice.dtos.responses;

import com.example.saudefacilagendamentoservice.enums.TipoUsuarioEnum;

public record LoginResponse(String email, String senha, TipoUsuarioEnum tipo) {
}
