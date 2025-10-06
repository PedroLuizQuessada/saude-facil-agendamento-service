package com.example.saudefacilagendamentoservice.datasources;

import com.example.saudefacilagendamentoservice.dtos.NotificacaoConsultaDto;

public interface NotificacaoDataSource { //TODO NotificacaoServiceRabbitImpl
    void notificarConsulta(NotificacaoConsultaDto notificacaoConsultaDto);
}
