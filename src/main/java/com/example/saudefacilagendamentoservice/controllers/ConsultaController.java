package com.example.saudefacilagendamentoservice.controllers;

import com.example.saudefacilagendamentoservice.datasources.ConsultaDataSource;
import com.example.saudefacilagendamentoservice.datasources.NotificacaoDataSource;
import com.example.saudefacilagendamentoservice.datasources.TokenDataSource;
import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import com.example.saudefacilagendamentoservice.entities.Consulta;
import com.example.saudefacilagendamentoservice.gateways.ConsultaGateway;
import com.example.saudefacilagendamentoservice.gateways.NotificacaoGateway;
import com.example.saudefacilagendamentoservice.gateways.TokenGateway;
import com.example.saudefacilagendamentoservice.gateways.UsuarioGateway;
import com.example.saudefacilagendamentoservice.mappers.ConsultaMapper;
import com.example.saudefacilagendamentoservice.usecases.*;
import dtos.requests.AlterarConsultaRequest;
import dtos.requests.CriarConsultaRequest;
import dtos.responses.ConsultaResponse;

import java.util.List;

public class ConsultaController {

    private final TokenDataSource tokenDataSource;
    private final UsuarioDataSource usuarioDataSource;
    private final ConsultaDataSource consultaDataSource;
    private final NotificacaoDataSource notificacaoDataSource;

    public ConsultaController(TokenDataSource tokenDataSource, UsuarioDataSource usuarioDataSource,
                              ConsultaDataSource consultaDataSource, NotificacaoDataSource notificacaoDataSource) {
        this.tokenDataSource = tokenDataSource;
        this.usuarioDataSource = usuarioDataSource;
        this.consultaDataSource = consultaDataSource;
        this.notificacaoDataSource = notificacaoDataSource;
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

    public List<ConsultaResponse> listarConsultasPorToken(int page, int size, String token) {
        TokenGateway tokenGateway = new TokenGateway(tokenDataSource);
        ConsultaGateway consultaGateway = new ConsultaGateway(consultaDataSource);
        ListarConsultasPorTokenUseCase useCase = new ListarConsultasPorTokenUseCase(tokenGateway, consultaGateway);
        List<Consulta> consultaList = useCase.execute(page, size, token);
        return consultaList.stream().map(ConsultaMapper::toResponse).toList();
    }

    public void criarConsulta(CriarConsultaRequest request) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        ConsultaGateway consultaGateway = new ConsultaGateway(consultaDataSource);
        NotificacaoGateway notificacaoGateway = new NotificacaoGateway(notificacaoDataSource);
        CriarConsultaUseCase useCase = new CriarConsultaUseCase(usuarioGateway, consultaGateway, notificacaoGateway);
        useCase.execute(request);
    }

    public void alterarConsulta(Long id, AlterarConsultaRequest request) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        ConsultaGateway consultaGateway = new ConsultaGateway(consultaDataSource);
        NotificacaoGateway notificacaoGateway = new NotificacaoGateway(notificacaoDataSource);
        AlterarConsultaUseCase useCase = new AlterarConsultaUseCase(usuarioGateway, consultaGateway, notificacaoGateway);
        useCase.execute(id, request);
    }
}
