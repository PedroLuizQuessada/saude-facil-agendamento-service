package com.example.saudefacilagendamentoservice.mappers;

import com.example.saudefacilagendamentoservice.dtos.ConsultaDto;
import com.example.saudefacilagendamentoservice.dtos.responses.ConsultaResponse;
import com.example.saudefacilagendamentoservice.entities.Consulta;

import java.util.Objects;

public class ConsultaMapper {

    public static Consulta toEntity(ConsultaDto consultaDto) {
        return new Consulta(consultaDto.id(),
                !Objects.isNull(consultaDto.medico()) ? UsuarioMapper.toEntity(consultaDto.medico()) : null,
                !Objects.isNull(consultaDto.paciente()) ? UsuarioMapper.toEntity(consultaDto.paciente()) : null,
                consultaDto.data());
    }

    public static ConsultaResponse toResponse(Consulta consulta) {
        return new ConsultaResponse(consulta.getId(),
                !Objects.isNull(consulta.getMedico()) ? UsuarioMapper.toResponse(consulta.getMedico()) : null,
                !Objects.isNull(consulta.getPaciente()) ? UsuarioMapper.toResponse(consulta.getPaciente()) : null,
                consulta.getData(), consulta.getPrescricao());
    }
}
