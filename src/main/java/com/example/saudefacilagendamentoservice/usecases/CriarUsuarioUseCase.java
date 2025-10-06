package com.example.saudefacilagendamentoservice.usecases;

import com.example.saudefacilagendamentoservice.dtos.UsuarioDto;
import com.example.saudefacilagendamentoservice.dtos.requests.CriarUsuarioRequest;
import com.example.saudefacilagendamentoservice.entities.Usuario;
import com.example.saudefacilagendamentoservice.exceptions.BadArgumentException;
import com.example.saudefacilagendamentoservice.exceptions.UsuarioNaoEncontradoException;
import com.example.saudefacilagendamentoservice.gateways.UsuarioGateway;
import com.example.saudefacilagendamentoservice.mappers.UsuarioMapper;

public class CriarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public CriarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public void execute(CriarUsuarioRequest request) {

        try {
            usuarioGateway.encontrarUsuarioPorEmail(request.email());
            throw new BadArgumentException("O e-mail já está sendo utilizado.");
        }
        catch (UsuarioNaoEncontradoException ignored) {}

        UsuarioDto usuarioDto = UsuarioMapper.toDto(request);
        Usuario usuario = UsuarioMapper.toEntity(usuarioDto, true);

        usuarioGateway.criarUsuario(UsuarioMapper.toDto(usuario));
    }
}
