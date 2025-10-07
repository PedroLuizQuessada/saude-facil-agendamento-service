package com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.mappers;

import com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.models.ConsultaJpa;
import dtos.ConsultaDto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
@Profile("jpa")
public class ConsultaJpaDtoMapper {

    private final UsuarioJpaDtoMapper usuarioJpaDtoMapper;

    public ConsultaJpa toConsultaJpa(ConsultaDto consultaDto) {
        return new ConsultaJpa(consultaDto.id(),
                !Objects.isNull(consultaDto.medico()) ? usuarioJpaDtoMapper.toUsuarioJpa(consultaDto.medico()) : null,
                !Objects.isNull(consultaDto.paciente()) ? usuarioJpaDtoMapper.toUsuarioJpa(consultaDto.paciente()) : null,
                consultaDto.data(), consultaDto.prescricao());
    }

    public ConsultaDto toConsultaDto(ConsultaJpa consultaJpa) {
        return new ConsultaDto(consultaJpa.getId(),
                !Objects.isNull(consultaJpa.getMedico()) ? usuarioJpaDtoMapper.toUsuarioDto(consultaJpa.getMedico()) : null,
                !Objects.isNull(consultaJpa.getPaciente()) ? usuarioJpaDtoMapper.toUsuarioDto(consultaJpa.getPaciente()) : null,
                consultaJpa.getData(), consultaJpa.getPrescricao());
    }

}
