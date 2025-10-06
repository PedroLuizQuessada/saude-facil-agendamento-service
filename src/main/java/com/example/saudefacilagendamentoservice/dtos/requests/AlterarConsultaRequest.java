package com.example.saudefacilagendamentoservice.dtos.requests;

import java.time.LocalDateTime;

public record AlterarConsultaRequest(Long medico, Long paciente, LocalDateTime data, String prescricao) {
}
