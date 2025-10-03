package com.example.saudefacilagendamentoservice.datasources;

import com.example.saudefacilagendamentoservice.dtos.ConsultaDto;

import java.util.List;

public interface ConsultaDataSource {
    List<ConsultaDto> listarConsultasPorPacienteId(int page, int size, Long id);
    List<ConsultaDto> listarConsultasFuturasPorPacienteId(int page, int size, Long id);
    List<ConsultaDto> listarConsultasPorPacienteEmail(int page, int size, String email);
}
