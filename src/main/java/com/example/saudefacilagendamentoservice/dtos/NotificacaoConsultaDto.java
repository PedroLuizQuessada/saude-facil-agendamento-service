package com.example.saudefacilagendamentoservice.dtos;

import java.time.LocalDateTime;

public record NotificacaoConsultaDto(String email, String nomeMedico, LocalDateTime data) {
}
