package com.example.saudefacilagendamentoservice.mappers;

import com.example.saudefacilagendamentoservice.dtos.NotificacaoConsultaDto;
import com.example.saudefacilagendamentoservice.dtos.requests.AlterarConsultaRequest;
import com.example.saudefacilagendamentoservice.dtos.requests.CriarConsultaRequest;
import com.example.saudefacilagendamentoservice.entities.Usuario;

public class NotificacaoConsultaMapper {

    private NotificacaoConsultaMapper(){}

    public static NotificacaoConsultaDto toDto(Usuario paciente, Usuario medico, CriarConsultaRequest request) {
        return new NotificacaoConsultaDto(paciente.getEmail(), medico.getNome(), request.data());
    }

    public static NotificacaoConsultaDto toDto(Usuario paciente, Usuario medico, AlterarConsultaRequest request) {
        return new NotificacaoConsultaDto(paciente.getEmail(), medico.getNome(), request.data());
    }

}
