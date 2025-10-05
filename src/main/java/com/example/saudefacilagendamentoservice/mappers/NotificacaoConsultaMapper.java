package com.example.saudefacilagendamentoservice.mappers;

import com.example.saudefacilagendamentoservice.dtos.NotificacaoConsultaDto;
import com.example.saudefacilagendamentoservice.dtos.requests.CriarConsultaRequest;
import com.example.saudefacilagendamentoservice.entities.Usuario;

public class NotificacaoConsultaMapper {

    public static NotificacaoConsultaDto toDto(Usuario paciente, CriarConsultaRequest request) {
        return new NotificacaoConsultaDto(paciente.getEmail(), request.data());
    }

}
