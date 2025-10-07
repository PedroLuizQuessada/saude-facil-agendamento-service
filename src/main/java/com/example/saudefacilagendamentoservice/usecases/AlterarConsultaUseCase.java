package com.example.saudefacilagendamentoservice.usecases;

import com.example.saudefacilagendamentoservice.entities.Consulta;
import com.example.saudefacilagendamentoservice.entities.Usuario;
import com.example.saudefacilagendamentoservice.exceptions.UsuarioOcupadoException;
import com.example.saudefacilagendamentoservice.gateways.ConsultaGateway;
import com.example.saudefacilagendamentoservice.gateways.NotificacaoGateway;
import com.example.saudefacilagendamentoservice.gateways.UsuarioGateway;
import com.example.saudefacilagendamentoservice.mappers.ConsultaMapper;
import com.example.saudefacilagendamentoservice.mappers.NotificacaoConsultaMapper;
import dtos.requests.AlterarConsultaRequest;

import java.util.List;
import java.util.Objects;

public class AlterarConsultaUseCase {

    private final UsuarioGateway usuarioGateway;
    private final ConsultaGateway consultaGateway;
    private final NotificacaoGateway notificacaoGateway;

    public AlterarConsultaUseCase(UsuarioGateway usuarioGateway, ConsultaGateway consultaGateway, NotificacaoGateway notificacaoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.consultaGateway = consultaGateway;
        this.notificacaoGateway = notificacaoGateway;
    }

    public void execute(Long id, AlterarConsultaRequest request) {

        Consulta consulta = consultaGateway.consultarConsultaPorId(id);
        Usuario medico = usuarioGateway.encontrarUsuarioPorId(request.medico());
        Usuario paciente = usuarioGateway.encontrarUsuarioPorId(request.paciente());

        List<Consulta> consultaListMedico = consultaGateway.listarConsultasPorHorarioEUsuarioId(request.data(), request.data().plusMinutes(30L), medico.getId());
        for (Consulta consultaMedico : consultaListMedico) {
            if (!Objects.equals(consultaMedico.getId(), id))
                throw new UsuarioOcupadoException("Médico já possui uma consulta agendada para este horário.");
        }

        List<Consulta> consultaListPaciente = consultaGateway.listarConsultasPorHorarioEUsuarioId(request.data(), request.data().plusMinutes(30L), paciente.getId());
        for (Consulta consultaPaciente : consultaListPaciente) {
            if (!Objects.equals(consultaPaciente.getId(), id))
                throw new UsuarioOcupadoException("Paciente já possui uma consulta agendada para este horário.");
        }

        consulta.setMedico(medico);
        consulta.setData(request.data());
        consulta.setPrescricao(request.prescricao());

        consultaGateway.alterarConsulta(ConsultaMapper.toDto(consulta));
        notificacaoGateway.notificarConsulta(NotificacaoConsultaMapper.toDto(paciente, medico, request));
    }
}
