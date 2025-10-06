package com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.repos;

import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import com.example.saudefacilagendamentoservice.dtos.UsuarioDto;
import com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.mappers.UsuarioJpaDtoMapper;
import com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.models.UsuarioJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Profile("jpa")
public class UsuarioRepositoryJpaImpl implements UsuarioDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsuarioJpaDtoMapper usuarioJpaDtoMapper;

    @Override
    public Optional<UsuarioDto> encontrarUsuarioPorId(Long id) {
        Query query = entityManager.createQuery("SELECT usuario FROM UsuarioJpa usuario WHERE usuario.id = :id");
        query.setParameter("id", id);
        try {
            UsuarioJpa usuarioJpa = (UsuarioJpa) query.getSingleResult();
            return Optional.ofNullable(usuarioJpaDtoMapper.toUsuarioDto(usuarioJpa));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UsuarioDto> encontrarUsuarioPorEmail(String email) {
        Query query = entityManager.createQuery("SELECT usuario FROM UsuarioJpa usuario WHERE usuario.email = :email");
        query.setParameter("email", email);
        try {
            UsuarioJpa usuarioJpa = (UsuarioJpa) query.getSingleResult();
            return Optional.ofNullable(usuarioJpaDtoMapper.toUsuarioDto(usuarioJpa));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void criarUsuario(UsuarioDto usuarioDto) {
        UsuarioJpa usuarioJpa = usuarioJpaDtoMapper.toUsuarioJpa(usuarioDto);
        entityManager.merge(usuarioJpa);
    }
}
