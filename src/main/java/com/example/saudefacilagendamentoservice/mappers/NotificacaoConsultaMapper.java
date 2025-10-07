package com.example.saudefacilagendamentoservice.mappers;

import com.example.saudefacilagendamentoservice.entities.Usuario;
import dtos.NotificacaoConsultaDto;
import dtos.requests.AlterarConsultaRequest;
import dtos.requests.CriarConsultaRequest;

public class NotificacaoConsultaMapper {

    private NotificacaoConsultaMapper(){}

    public static NotificacaoConsultaDto toDto(Usuario paciente, Usuario medico, CriarConsultaRequest request) {
        return new NotificacaoConsultaDto(paciente.getEmail(), medico.getNome(), request.data());
    }

    public static NotificacaoConsultaDto toDto(Usuario paciente, Usuario medico, AlterarConsultaRequest request) {
        return new NotificacaoConsultaDto(paciente.getEmail(), medico.getNome(), request.data());
    }

}
