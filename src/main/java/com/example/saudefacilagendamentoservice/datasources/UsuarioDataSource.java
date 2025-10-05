package com.example.saudefacilagendamentoservice.datasources;

import com.example.saudefacilagendamentoservice.dtos.UsuarioDto;

import java.util.Optional;

public interface UsuarioDataSource {
    Optional<UsuarioDto> encontrarUsuarioPorId(Long id);
}
