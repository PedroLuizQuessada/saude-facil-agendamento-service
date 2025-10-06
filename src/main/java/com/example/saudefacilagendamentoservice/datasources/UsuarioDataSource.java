package com.example.saudefacilagendamentoservice.datasources;

import com.example.saudefacilagendamentoservice.dtos.UsuarioDto;

import java.util.Optional;

public interface UsuarioDataSource {
    Optional<UsuarioDto> encontrarUsuarioPorId(Long id);
    Optional<UsuarioDto> encontrarUsuarioPorEmail(String email);
    void criarUsuario(UsuarioDto usuarioDto);
}
