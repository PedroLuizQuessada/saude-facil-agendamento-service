package com.example.saudefacilagendamentoservice.datasources;

import dtos.NotificacaoConsultaDto;

public interface NotificacaoDataSource {
    void notificarConsulta(NotificacaoConsultaDto notificacaoConsultaDto);
}
