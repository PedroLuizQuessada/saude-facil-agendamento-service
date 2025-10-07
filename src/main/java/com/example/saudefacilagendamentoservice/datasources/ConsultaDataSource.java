package com.example.saudefacilagendamentoservice.datasources;

import dtos.ConsultaDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultaDataSource {
    List<ConsultaDto> listarConsultasPorPacienteId(int page, int size, Long id);
    List<ConsultaDto> listarConsultasFuturasPorPacienteId(int page, int size, Long id);
    List<ConsultaDto> listarConsultasPorPacienteEmail(int page, int size, String email);
    List<ConsultaDto> listarConsultasPorHorarioEUsuarioId(LocalDateTime dataInicio, LocalDateTime dataFim, Long id);
    void criarConsulta(ConsultaDto consultaDto);
    Optional<ConsultaDto> consultarConsultaPorId(Long id);
    void alterarConsulta(ConsultaDto consultaDto);
}
