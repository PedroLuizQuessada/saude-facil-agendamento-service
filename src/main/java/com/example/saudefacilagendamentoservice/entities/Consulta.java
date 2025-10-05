package com.example.saudefacilagendamentoservice.entities;

import com.example.saudefacilagendamentoservice.enums.TipoUsuarioEnum;
import com.example.saudefacilagendamentoservice.exceptions.BadArgumentException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Consulta {

    private final Long id;
    private final Usuario medico;
    private final Usuario paciente;
    private final LocalDateTime data;
    private String prescricao;

    public Consulta(Long id, Usuario medico, Usuario paciente, LocalDateTime data) {

        validarMedico(medico);
        validarPaciente(paciente);

        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
    }

    private void validarMedico(Usuario medico) {
        if (Objects.isNull(medico))
            throw new BadArgumentException("A consulta deve possuir um médico.");

        if (!medico.getTipo().equals(TipoUsuarioEnum.MEDICO))
            throw new BadArgumentException("Médico inválido.");
    }

    private void validarPaciente(Usuario paciente) {
        if (Objects.isNull(paciente))
            throw new BadArgumentException("A consulta deve possuir um paciente.");

        if (!paciente.getTipo().equals(TipoUsuarioEnum.PACIENTE))
            throw new BadArgumentException("Paciente inválido.");
    }
}
