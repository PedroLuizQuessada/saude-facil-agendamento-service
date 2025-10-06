package com.example.saudefacilagendamentoservice.mappers;

import com.example.saudefacilagendamentoservice.dtos.ConsultaDto;
import com.example.saudefacilagendamentoservice.dtos.requests.CriarConsultaRequest;
import com.example.saudefacilagendamentoservice.dtos.responses.ConsultaResponse;
import com.example.saudefacilagendamentoservice.entities.Consulta;
import com.example.saudefacilagendamentoservice.entities.Usuario;

import java.util.Objects;

public class ConsultaMapper {

    public static Consulta toEntity(ConsultaDto consultaDto) {
        return new Consulta(consultaDto.id(),
                !Objects.isNull(consultaDto.medico()) ? UsuarioMapper.toEntity(consultaDto.medico(), false) : null,
                !Objects.isNull(consultaDto.paciente()) ? UsuarioMapper.toEntity(consultaDto.paciente(), false) : null,
                consultaDto.data());
    }

    public static ConsultaResponse toResponse(Consulta consulta) {
        return new ConsultaResponse(consulta.getId(),
                !Objects.isNull(consulta.getMedico()) ? UsuarioMapper.toResponse(consulta.getMedico()) : null,
                !Objects.isNull(consulta.getPaciente()) ? UsuarioMapper.toResponse(consulta.getPaciente()) : null,
                consulta.getData(), consulta.getPrescricao());
    }

    public static ConsultaDto toDto(Usuario medico, Usuario paciente, CriarConsultaRequest request) {
        return new ConsultaDto(null,
                !Objects.isNull(medico) ? UsuarioMapper.toDto(medico) : null,
                !Objects.isNull(paciente) ? UsuarioMapper.toDto(paciente) : null,
                request.data(), null);
    }

    public static ConsultaDto toDto(Consulta consulta) {
        return new ConsultaDto(consulta.getId(),
                !Objects.isNull(consulta.getMedico()) ? UsuarioMapper.toDto(consulta.getMedico()) : null,
                !Objects.isNull(consulta.getPaciente()) ? UsuarioMapper.toDto(consulta.getPaciente()) : null,
                consulta.getData(), consulta.getPrescricao());
    }
}
