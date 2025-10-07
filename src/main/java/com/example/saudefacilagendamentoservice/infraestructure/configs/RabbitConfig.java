package com.example.saudefacilagendamentoservice.infraestructure.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitConfig {

    @Value("${rabbitmq.exchange.nome}")
    private String nomeExchange;

    @Value("${rabbitmq.fila-notificacoes-consultas}")
    private String filaNotificacoesConsulta;

    private final AmqpAdmin amqpAdmin;

    public RabbitConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(nomeExchange);
    }

    private Binding relacionamento(Queue fila, DirectExchange trocaDireta) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, trocaDireta.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona() {
        Queue filaNotificacoesConsulta = this.fila(this.filaNotificacoesConsulta);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoNotificacoesConsulta = this.relacionamento(filaNotificacoesConsulta, troca);

        this.amqpAdmin.declareQueue(filaNotificacoesConsulta);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoNotificacoesConsulta);
    }

}
