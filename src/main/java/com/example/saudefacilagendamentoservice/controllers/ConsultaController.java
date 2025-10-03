package com.example.saudefacilagendamentoservice.controllers;

import com.example.saudefacilagendamentoservice.datasources.ConsultaDataSource;
import com.example.saudefacilagendamentoservice.dtos.responses.ConsultaResponse;
import com.example.saudefacilagendamentoservice.entities.Consulta;
import com.example.saudefacilagendamentoservice.gateways.ConsultaGateway;
import com.example.saudefacilagendamentoservice.mappers.ConsultaMapper;
import com.example.saudefacilagendamentoservice.usecases.ListarConsultasFuturasPorPacienteIdUseCase;
import com.example.saudefacilagendamentoservice.usecases.ListarConsultasPorPacienteIdUseCase;

import java.util.List;

public class ConsultaController {

    private final ConsultaDataSource consultaDataSource;

    public ConsultaController(ConsultaDataSource consultaDataSource) {
        this.consultaDataSource = consultaDataSource;
    }

    public List<ConsultaResponse> listarConsultasPorPacienteId(int page, int size, Long id) {
        ConsultaGateway consultaGateway = new ConsultaGateway(consultaDataSource);
        ListarConsultasPorPacienteIdUseCase useCase = new ListarConsultasPorPacienteIdUseCase(consultaGateway);
        List<Consulta> consultaList = useCase.execute(page, size, id);
        return consultaList.stream().map(ConsultaMapper::toResponse).toList();
    }

    public List<ConsultaResponse> listarConsultasFuturasPorPacienteId(int page, int size, Long id) {
        ConsultaGateway consultaGateway = new ConsultaGateway(consultaDataSource);
        ListarConsultasFuturasPorPacienteIdUseCase useCase = new ListarConsultasFuturasPorPacienteIdUseCase(consultaGateway);
        List<Consulta> consultaList = useCase.execute(page, size, id);
        return consultaList.stream().map(ConsultaMapper::toResponse).toList();
    }
}
