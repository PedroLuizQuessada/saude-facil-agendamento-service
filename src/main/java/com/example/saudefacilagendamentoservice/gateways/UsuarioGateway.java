package com.example.saudefacilagendamentoservice.gateways;

import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import com.example.saudefacilagendamentoservice.dtos.UsuarioDto;
import com.example.saudefacilagendamentoservice.entities.Usuario;
import com.example.saudefacilagendamentoservice.exceptions.UsuarioNaoEncontradoException;
import com.example.saudefacilagendamentoservice.mappers.UsuarioMapper;

import java.util.Optional;

public class UsuarioGateway {

    private final UsuarioDataSource usuarioDataSource;

    public UsuarioGateway(UsuarioDataSource usuarioDataSource) {
        this.usuarioDataSource = usuarioDataSource;
    }

    public Usuario encontrarUsuarioPorId(Long id) {
        Optional<UsuarioDto> optionalUsuarioDto = usuarioDataSource.encontrarUsuarioPorId(id);

        if (optionalUsuarioDto.isEmpty())
            throw new UsuarioNaoEncontradoException(String.format("Usuário %d não encontrado.", id));

        return UsuarioMapper.toEntity(optionalUsuarioDto.get());
    }

    public Usuario encontrarUsuarioPorEmail(String email) {
        Optional<UsuarioDto> optionalUsuarioDto = usuarioDataSource.encontrarUsuarioPorEmail(email);

        if (optionalUsuarioDto.isEmpty())
            throw new UsuarioNaoEncontradoException(String.format("Usuário %s não encontrado.", email));

        return UsuarioMapper.toEntity(optionalUsuarioDto.get());
    }

    public void criarUsuario(UsuarioDto usuarioDto) {
        usuarioDataSource.criarUsuario(usuarioDto);
    }
}
