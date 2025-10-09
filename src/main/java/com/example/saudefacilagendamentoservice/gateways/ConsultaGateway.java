package com.example.saudefacilagendamentoservice.gateways;

import com.example.saudefacilagendamentoservice.datasources.ConsultaDataSource;
import com.example.saudefacilagendamentoservice.entities.Consulta;
import com.example.saudefacilagendamentoservice.exceptions.ConsultaNaoEncontradaException;
import com.example.saudefacilagendamentoservice.mappers.ConsultaMapper;
import dtos.ConsultaDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ConsultaGateway {

    private final ConsultaDataSource consultaDataSource;

    public ConsultaGateway(ConsultaDataSource consultaDataSource) {
        this.consultaDataSource = consultaDataSource;
    }

    public List<Consulta> listarConsultasPorPacienteId(int page, int size, Long id) {
        List<ConsultaDto> consultaDtoList = consultaDataSource.listarConsultasPorPacienteId(page, size, id);
        return consultaDtoList.stream().map(ConsultaMapper::toEntity).toList();
    }

    public List<Consulta> listarConsultasFuturasPorPacienteId(int page, int size, Long id) {
        List<ConsultaDto> consultaDtoList = consultaDataSource.listarConsultasFuturasPorPacienteId(page, size, id);
        return consultaDtoList.stream().map(ConsultaMapper::toEntity).toList();
    }

    public List<Consulta> listarConsultasPorPacienteEmail(int page, int size, String email) {
        List<ConsultaDto> consultaDtoList = consultaDataSource.listarConsultasPorPacienteEmail(page, size, email);
        return consultaDtoList.stream().map(ConsultaMapper::toEntity).toList();
    }

    public List<Consulta> listarConsultasPorHorarioEMedicoId(LocalDateTime dataInicio, LocalDateTime dataFim, Long medicoId) {
        List<ConsultaDto> consultaDtoList = consultaDataSource.listarConsultasPorHorarioEMedicoId(dataInicio, dataFim, medicoId);
        return consultaDtoList.stream().map(ConsultaMapper::toEntity).toList();
    }

    public List<Consulta> listarConsultasPorHorarioEPacienteId(LocalDateTime dataInicio, LocalDateTime dataFim, Long pacienteId) {
        List<ConsultaDto> consultaDtoList = consultaDataSource.listarConsultasPorHorarioEPacienteId(dataInicio, dataFim, pacienteId);
        return consultaDtoList.stream().map(ConsultaMapper::toEntity).toList();
    }

    public void criarConsulta(ConsultaDto consultaDto) {
        consultaDataSource.criarConsulta(consultaDto);
    }

    public Consulta consultarConsultaPorId(Long id) {
        Optional<ConsultaDto> optionalConsultaDto = consultaDataSource.consultarConsultaPorId(id);

        if (optionalConsultaDto.isEmpty())
            throw new ConsultaNaoEncontradaException(String.format("Consulta %d não encontrada", id));

        return ConsultaMapper.toEntity(optionalConsultaDto.get());
    }

    public void alterarConsulta(ConsultaDto consultaDto) {
        consultaDataSource.alterarConsulta(consultaDto);
    }
}
