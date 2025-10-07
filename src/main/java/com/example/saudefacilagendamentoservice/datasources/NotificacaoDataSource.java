package com.example.saudefacilagendamentoservice.datasources;

import com.example.saudefacilagendamentoservice.dtos.NotificacaoConsultaDto;

public interface NotificacaoDataSource {
    void notificarConsulta(NotificacaoConsultaDto notificacaoConsultaDto);
}
