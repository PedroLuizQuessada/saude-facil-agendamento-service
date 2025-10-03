package com.example.saudefacilagendamentoservice.dtos;

import java.time.LocalDateTime;

public record ConsultaDto(Long id, UsuarioDto medico, UsuarioDto paciente, LocalDateTime data, String prescricao) {
}
