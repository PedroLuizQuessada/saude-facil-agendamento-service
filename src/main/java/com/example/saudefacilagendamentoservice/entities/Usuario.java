package com.example.saudefacilagendamentoservice.entities;

import com.example.saudefacilagendamentoservice.enums.TipoUsuarioEnum;
import com.example.saudefacilagendamentoservice.exceptions.BadArgumentException;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

@Getter
public class Usuario {

    private final Long id;
    private final String nome;
    private final String email;
    private final String senha;
    private final TipoUsuarioEnum tipo;

    public Usuario(Long id, String nome, String email, String senha, TipoUsuarioEnum tipo) {

        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);
        validarTipo(tipo);

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    private void validarNome(String nome) {
        if (Objects.isNull(nome) || nome.isEmpty())
            throw new BadArgumentException("O usuário deve possuir um nome.");
    }

    private void validarEmail(String email) {
        if (Objects.isNull(email) || email.isEmpty())
            throw new BadArgumentException("O usuário deve possuir um e-mail.");

        if (!EmailValidator.getInstance().isValid(email))
            throw new BadArgumentException("E-mail inválido.");
    }

    private void validarSenha(String senha) {
        if (Objects.isNull(senha) || senha.length() < 6)
            throw new BadArgumentException("A senha do usuário deve possuir ao menos 6 caracteres.");
    }

    private void validarTipo(TipoUsuarioEnum tipo) {
        if (Objects.isNull(tipo))
            throw new BadArgumentException("O usuário deve possuir um tipo.");
    }
}
