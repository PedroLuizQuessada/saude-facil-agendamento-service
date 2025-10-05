package com.example.saudefacilagendamentoservice.usecases;

import com.example.saudefacilagendamentoservice.dtos.requests.CriarConsultaRequest;
import com.example.saudefacilagendamentoservice.entities.Usuario;
import com.example.saudefacilagendamentoservice.exceptions.ConsultaNaoEncontradaException;
import com.example.saudefacilagendamentoservice.exceptions.UsuarioOcupadoException;
import com.example.saudefacilagendamentoservice.gateways.ConsultaGateway;
import com.example.saudefacilagendamentoservice.gateways.UsuarioGateway;
import com.example.saudefacilagendamentoservice.mappers.ConsultaMapper;

public class CriarConsultaUseCase {

    private final UsuarioGateway usuarioGateway;
    private final ConsultaGateway consultaGateway;

    public CriarConsultaUseCase(UsuarioGateway usuarioGateway, ConsultaGateway consultaGateway) {
        this.usuarioGateway = usuarioGateway;
        this.consultaGateway = consultaGateway;
    }

    public void execute(CriarConsultaRequest request) {
        Usuario medico = usuarioGateway.encontrarUsuarioPorId(request.medico());
        Usuario paciente = usuarioGateway.encontrarUsuarioPorId(request.paciente());

        try {
            consultaGateway.consultarConsultaPorHorarioEUsuarioId(request.data(), request.data().plusMinutes(30L), medico.getId());
            throw new UsuarioOcupadoException("Médico já possui uma consulta agendada para este horário.");
        }
        catch (ConsultaNaoEncontradaException ignored) {}

        try {
            consultaGateway.consultarConsultaPorHorarioEUsuarioId(request.data(), request.data().plusMinutes(30L), paciente.getId());
            throw new UsuarioOcupadoException("Paciente já possui uma consulta agendada para este horário.");
        }
        catch (ConsultaNaoEncontradaException ignored) {}

        consultaGateway.criarConsulta(ConsultaMapper.toDto(medico, paciente, request));
    }
}
