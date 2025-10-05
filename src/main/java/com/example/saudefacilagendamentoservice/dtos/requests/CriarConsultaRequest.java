package com.example.saudefacilagendamentoservice.dtos.requests;

import java.time.LocalDateTime;

public record CriarConsultaRequest(Long medico, Long paciente, LocalDateTime data) {
}
