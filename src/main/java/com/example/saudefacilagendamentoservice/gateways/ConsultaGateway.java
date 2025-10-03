package com.example.saudefacilagendamentoservice.gateways;

import com.example.saudefacilagendamentoservice.datasources.ConsultaDataSource;
import com.example.saudefacilagendamentoservice.dtos.ConsultaDto;
import com.example.saudefacilagendamentoservice.entities.Consulta;
import com.example.saudefacilagendamentoservice.mappers.ConsultaMapper;

import java.util.List;

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
}
