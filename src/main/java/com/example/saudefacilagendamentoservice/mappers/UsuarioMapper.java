package com.example.saudefacilagendamentoservice.mappers;

import com.example.saudefacilagendamentoservice.dtos.UsuarioDto;
import com.example.saudefacilagendamentoservice.dtos.requests.CriarUsuarioRequest;
import com.example.saudefacilagendamentoservice.dtos.responses.UsuarioResponse;
import com.example.saudefacilagendamentoservice.entities.Usuario;

public class UsuarioMapper {

    private UsuarioMapper(){}

    public static Usuario toEntity(UsuarioDto usuarioDto, boolean encodePassword) {
        return new Usuario(usuarioDto.id(), usuarioDto.nome(), usuarioDto.email(), usuarioDto.senha(), usuarioDto.tipo(), encodePassword);
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTipo());
    }

    public static UsuarioDto toDto(CriarUsuarioRequest request) {
        return new UsuarioDto(null, request.nome(), request.email(), request.senha(), request.tipo());
    }

    public static UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getTipo());
    }

}
