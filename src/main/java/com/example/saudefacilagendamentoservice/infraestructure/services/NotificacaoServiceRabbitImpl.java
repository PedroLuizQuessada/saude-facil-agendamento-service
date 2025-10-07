package com.example.saudefacilagendamentoservice.infraestructure.services;

import com.example.saudefacilagendamentoservice.datasources.NotificacaoDataSource;
import com.example.saudefacilagendamentoservice.dtos.NotificacaoConsultaDto;
import com.example.saudefacilagendamentoservice.infraestructure.exceptions.RabbitException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("rabbit")
public class NotificacaoServiceRabbitImpl implements NotificacaoDataSource {

    @Value("${rabbitmq.fila-notificacoes-consultas}")
    private String filaNotificacoesConsulta;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void notificarConsulta(NotificacaoConsultaDto notificacaoConsultaDto) {
        try {
            this.rabbitTemplate.convertAndSend(filaNotificacoesConsulta, objectMapper.writeValueAsString(notificacaoConsultaDto));
        } catch (JsonProcessingException e) {
            throw new RabbitException(e.getMessage());
        }
    }
}
