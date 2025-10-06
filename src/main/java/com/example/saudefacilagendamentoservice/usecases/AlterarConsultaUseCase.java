package com.example.saudefacilagendamentoservice.usecases;

import com.example.saudefacilagendamentoservice.dtos.requests.AlterarConsultaRequest;
import com.example.saudefacilagendamentoservice.entities.Consulta;
import com.example.saudefacilagendamentoservice.entities.Usuario;
import com.example.saudefacilagendamentoservice.exceptions.UsuarioOcupadoException;
import com.example.saudefacilagendamentoservice.gateways.ConsultaGateway;
import com.example.saudefacilagendamentoservice.gateways.NotificacaoGateway;
import com.example.saudefacilagendamentoservice.gateways.TokenGateway;
import com.example.saudefacilagendamentoservice.gateways.UsuarioGateway;
import com.example.saudefacilagendamentoservice.mappers.ConsultaMapper;
import com.example.saudefacilagendamentoservice.mappers.NotificacaoConsultaMapper;

import java.util.List;
import java.util.Objects;

public class AlterarConsultaUseCase {

    private final UsuarioGateway usuarioGateway;
    private final TokenGateway tokenGateway;
    private final ConsultaGateway consultaGateway;
    private final NotificacaoGateway notificacaoGateway;

    public AlterarConsultaUseCase(UsuarioGateway usuarioGateway, TokenGateway tokenGateway, ConsultaGateway consultaGateway,
                                  NotificacaoGateway notificacaoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.tokenGateway = tokenGateway;
        this.consultaGateway = consultaGateway;
        this.notificacaoGateway = notificacaoGateway;
    }

    public void execute(Long id, String token, AlterarConsultaRequest request) {

        Consulta consulta = consultaGateway.consultarConsultaPorId(id);

        Usuario medico = usuarioGateway.encontrarUsuarioPorId(request.medico());

        String pacienteEmail = tokenGateway.getEmail(token);
        Usuario paciente = usuarioGateway.encontrarUsuarioPorEmail(pacienteEmail);

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
