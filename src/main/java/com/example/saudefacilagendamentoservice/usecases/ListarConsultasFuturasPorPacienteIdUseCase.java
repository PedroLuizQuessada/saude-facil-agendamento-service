package com.example.saudefacilagendamentoservice.usecases;

import com.example.saudefacilagendamentoservice.entities.Consulta;
import com.example.saudefacilagendamentoservice.gateways.ConsultaGateway;

import java.util.List;

public class ListarConsultasFuturasPorPacienteIdUseCase {

    private final ConsultaGateway consultaGateway;

    public ListarConsultasFuturasPorPacienteIdUseCase(ConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    public List<Consulta> execute(int page, int size, Long id) {
        return consultaGateway.listarConsultasFuturasPorPacienteId(page, size, id);
    }
}
