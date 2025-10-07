package com.example.saudefacilagendamentoservice.entities;

import com.example.saudefacilagendamentoservice.exceptions.BadArgumentException;
import enums.TipoUsuarioEnum;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Consulta {

    private final Long id;
    private Usuario medico;
    private final Usuario paciente;
    private LocalDateTime data;
    private String prescricao;

    public Consulta(Long id, Usuario medico, Usuario paciente, LocalDateTime data) {

        validarMedico(medico);
        validarPaciente(paciente);
        validarData(data);

        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
    }

    public void setMedico(Usuario medico) {
        validarMedico(medico);
        this.medico = medico;
    }

    public void setData(LocalDateTime data) {
        validarData(data);
        this.data = data;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
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

    private void validarData(LocalDateTime data) {
        if (Objects.isNull(data))
            throw new BadArgumentException("A consulta deve possuir uma data.");
    }
}
