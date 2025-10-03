package com.example.saudefacilagendamentoservice.usecases;

import com.example.saudefacilagendamentoservice.entities.Consulta;
import com.example.saudefacilagendamentoservice.gateways.ConsultaGateway;
import com.example.saudefacilagendamentoservice.gateways.TokenGateway;

import java.util.List;

public class ListarConsultasPorTokenUseCase {

    private final TokenGateway tokenGateway;
    private final ConsultaGateway consultaGateway;


    public ListarConsultasPorTokenUseCase(TokenGateway tokenGateway, ConsultaGateway consultaGateway) {
        this.tokenGateway = tokenGateway;
        this.consultaGateway = consultaGateway;
    }

    public List<Consulta> execute(int page, int size, String token) {
        String email = tokenGateway.getEmail(token);
        return consultaGateway.listarConsultasPorPacienteEmail(page, size, email);
    }
}
