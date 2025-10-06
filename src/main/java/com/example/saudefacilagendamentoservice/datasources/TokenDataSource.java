package com.example.saudefacilagendamentoservice.datasources;

import com.example.saudefacilagendamentoservice.dtos.TokenDto;

public interface TokenDataSource {
    TokenDto gerarToken(String tipoUsuario, String login);
    String getEmail(String token);
}
