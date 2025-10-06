package com.example.saudefacilagendamentoservice.gateways;

import com.example.saudefacilagendamentoservice.datasources.NotificacaoDataSource;
import com.example.saudefacilagendamentoservice.dtos.NotificacaoConsultaDto;

public class NotificacaoGateway {

    private final NotificacaoDataSource notificacaoDataSource;

    public NotificacaoGateway(NotificacaoDataSource notificacaoDataSource) {
        this.notificacaoDataSource = notificacaoDataSource;
    }

    public void notificarConsulta(NotificacaoConsultaDto notificacaoConsultaDto) {
        notificacaoDataSource.notificarConsulta(notificacaoConsultaDto);
    }
}
