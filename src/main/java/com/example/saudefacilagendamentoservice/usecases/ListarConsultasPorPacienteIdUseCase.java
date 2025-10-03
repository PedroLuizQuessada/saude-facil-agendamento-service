package com.example.saudefacilagendamentoservice.usecases;

import com.example.saudefacilagendamentoservice.entities.Consulta;
import com.example.saudefacilagendamentoservice.gateways.ConsultaGateway;

import java.util.List;

public class ListarConsultasPorPacienteIdUseCase {

    private final ConsultaGateway consultaGateway;

    public ListarConsultasPorPacienteIdUseCase(ConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    public List<Consulta> execute(int page, int size, Long id) {
        return consultaGateway.listarConsultasPorPacienteId(page, size, id);
    }
}
