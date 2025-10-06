package com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.models;

import com.example.saudefacilagendamentoservice.infraestructure.exceptions.BadJpaArgumentException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "consultas")
@Getter
@NoArgsConstructor
public class ConsultaJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private UsuarioJpa medico;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private UsuarioJpa paciente;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(length = 255)
    private String prescricao;

    public ConsultaJpa(Long id, UsuarioJpa medico, UsuarioJpa paciente, LocalDateTime data, String prescricao) {
        validarMedico(medico);
        validarPaciente(paciente);
        validarData(data);
        validarPrescricao(prescricao);

        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.prescricao = prescricao;
    }

    private void validarMedico(UsuarioJpa medico) {
        if (Objects.isNull(medico))
            throw new BadJpaArgumentException("A consulta deve possuir um médico para ser armazenado no banco de dados.");
    }

    private void validarPaciente(UsuarioJpa paciente) {
        if (Objects.isNull(paciente))
            throw new BadJpaArgumentException("A consulta deve possuir um paciente para ser armazenado no banco de dados.");
    }

    private void validarData(LocalDateTime data) {
        if (Objects.isNull(data))
            throw new BadJpaArgumentException("A consulta deve possuir uma data para ser armazenada no banco de dados.");
    }

    private void validarPrescricao(String prescricao) {
        if (prescricao.length() > 255)
            throw new BadJpaArgumentException("A prescrição deve possuir até 255 caracteres para ser armazenada no banco de dados.");
    }
}
