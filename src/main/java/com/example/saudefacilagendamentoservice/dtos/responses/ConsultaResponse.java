package com.example.saudefacilagendamentoservice.dtos.responses;

import java.time.LocalDateTime;

public record ConsultaResponse(Long id, UsuarioResponse medico, UsuarioResponse paciente, LocalDateTime data, String prescricao) {
}
