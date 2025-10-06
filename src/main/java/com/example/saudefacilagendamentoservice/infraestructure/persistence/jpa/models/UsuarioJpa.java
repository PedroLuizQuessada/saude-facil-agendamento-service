package com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.models;

import com.example.saudefacilagendamentoservice.infraestructure.exceptions.BadJpaArgumentException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
public class UsuarioJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8_bin")
    private String senha;

    @Column(nullable = false, length = 45)
    private String tipo;

    public UsuarioJpa(Long id, String nome, String email, String senha, String tipo) {
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
        if (Objects.isNull(nome))
            throw new BadJpaArgumentException("O usuário deve possuir um nome para ser armazenado no banco de dados.");

        if (nome.length() > 45)
            throw new BadJpaArgumentException("O nome do usuário deve possuir até 45 caracteres para ser armazenado no banco de dados.");
    }

    private void validarEmail(String email) {
        if (Objects.isNull(email))
            throw new BadJpaArgumentException("O usuário deve possuir um e-mail para ser armazenado no banco de dados.");

        if (email.length() > 45)
            throw new BadJpaArgumentException("O e-mail do usuário deve possuir até 45 caracteres para ser armazenado no banco de dados.");
    }

    private void validarSenha(String password) {
        if (Objects.isNull(password))
            throw new BadJpaArgumentException("O usuário deve possuir uma senha para ser armazenado no banco de dados.");

        if (password.length() > 255)
            throw new BadJpaArgumentException("Falha ao gerar senha criptografada do usuário, favor contactar o administrador.");
    }

    private void validarTipo(String tipo) {
        if (Objects.isNull(tipo))
            throw new BadJpaArgumentException("O usuário deve possuir tipo de usuário para ser armazenado no banco de dados.");
    }
}
