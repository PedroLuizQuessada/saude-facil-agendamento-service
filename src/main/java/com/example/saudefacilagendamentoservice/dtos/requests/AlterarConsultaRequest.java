package com.example.saudefacilagendamentoservice.dtos.requests;

import java.time.LocalDateTime;

public record AlterarConsultaRequest(Long medico, LocalDateTime data, String prescricao) {
}
