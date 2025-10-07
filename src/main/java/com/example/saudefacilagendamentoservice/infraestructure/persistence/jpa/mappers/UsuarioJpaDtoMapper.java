package com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.mappers;

import com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.models.UsuarioJpa;
import dtos.UsuarioDto;
import enums.TipoUsuarioEnum;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Profile("jpa")
public class UsuarioJpaDtoMapper {

    public UsuarioJpa toUsuarioJpa(UsuarioDto usuarioDto) {
        return new UsuarioJpa(usuarioDto.id(), usuarioDto.nome(), usuarioDto.email(), usuarioDto.senha(),
                String.valueOf(usuarioDto.tipo()));
    }

    public UsuarioDto toUsuarioDto(UsuarioJpa usuarioJpa) {
        return new UsuarioDto(usuarioJpa.getId(), usuarioJpa.getNome(), usuarioJpa.getEmail(), usuarioJpa.getSenha(),
                TipoUsuarioEnum.valueOf(usuarioJpa.getTipo()));
    }

}
