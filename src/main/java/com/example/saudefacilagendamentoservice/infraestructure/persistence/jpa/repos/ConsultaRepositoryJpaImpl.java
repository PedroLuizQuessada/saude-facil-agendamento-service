package com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.repos;

import com.example.saudefacilagendamentoservice.datasources.ConsultaDataSource;
import com.example.saudefacilagendamentoservice.dtos.ConsultaDto;
import com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.mappers.ConsultaJpaDtoMapper;
import com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.models.ConsultaJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jpa")
public class ConsultaRepositoryJpaImpl implements ConsultaDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ConsultaJpaDtoMapper consultaJpaDtoMapper;

    @Override
    public List<ConsultaDto> listarConsultasPorPacienteId(int page, int size, Long id) {
        Query query = entityManager.createQuery("SELECT consulta FROM ConsultaJpa consulta WHERE consulta.paciente.id = :pacienteId ORDER BY consulta.id");
        query.setParameter("pacienteId", id);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        List<ConsultaJpa> consultaJpaList = query.getResultList();
        return consultaJpaList.stream().map(consultaJpa -> consultaJpaDtoMapper.toConsultaDto(consultaJpa)).toList();
    }

    @Override
    public List<ConsultaDto> listarConsultasFuturasPorPacienteId(int page, int size, Long id) {
        Query query = entityManager.createQuery("SELECT consulta FROM ConsultaJpa consulta WHERE consulta.paciente.id = :pacienteId AND consulta.data > NOW() ORDER BY consulta.id");
        query.setParameter("pacienteId", id);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        List<ConsultaJpa> consultaJpaList = query.getResultList();
        return consultaJpaList.stream().map(consultaJpa -> consultaJpaDtoMapper.toConsultaDto(consultaJpa)).toList();
    }

    @Override
    public List<ConsultaDto> listarConsultasPorPacienteEmail(int page, int size, String email) {
        Query query = entityManager.createQuery("SELECT consulta FROM ConsultaJpa consulta WHERE consulta.paciente.id = :pacienteEmail ORDER BY consulta.id");
        query.setParameter("pacienteEmail", email);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        List<ConsultaJpa> consultaJpaList = query.getResultList();
        return consultaJpaList.stream().map(consultaJpa -> consultaJpaDtoMapper.toConsultaDto(consultaJpa)).toList();
    }

    @Override
    public List<ConsultaDto> listarConsultasPorHorarioEUsuarioId(LocalDateTime dataInicio, LocalDateTime dataFim, Long id) {
        Query query = entityManager.createQuery("SELECT consulta FROM ConsultaJpa consulta WHERE consulta.paciente.id = :pacienteId AND consulta.data >= :dataInicio AND consulta.data <= :dataFim ORDER BY consulta.id");
        query.setParameter("pacienteId", id);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        List<ConsultaJpa> consultaJpaList = query.getResultList();
        return consultaJpaList.stream().map(consultaJpa -> consultaJpaDtoMapper.toConsultaDto(consultaJpa)).toList();
    }

    @Override
    @Transactional
    public void criarConsulta(ConsultaDto consultaDto) {
        ConsultaJpa consultaJpa = consultaJpaDtoMapper.toConsultaJpa(consultaDto);
        entityManager.merge(consultaJpa);
    }

    @Override
    public Optional<ConsultaDto> consultarConsultaPorId(Long id) {
        Query query = entityManager.createQuery("SELECT consulta FROM ConsultaJpa consulta WHERE consulta.id = :id");
        query.setParameter("id", id);
        try {
            ConsultaJpa consultaJpa = (ConsultaJpa) query.getSingleResult();
            return Optional.ofNullable(consultaJpaDtoMapper.toConsultaDto(consultaJpa));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void alterarConsulta(ConsultaDto consultaDto) {
        ConsultaJpa consultaJpa = consultaJpaDtoMapper.toConsultaJpa(consultaDto);
        entityManager.merge(consultaJpa);
    }
}
