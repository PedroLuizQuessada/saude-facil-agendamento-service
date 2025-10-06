package com.example.saudefacilagendamentoservice.infraestructure.configs;

import com.example.saudefacilagendamentoservice.datasources.ConsultaDataSource;
import com.example.saudefacilagendamentoservice.datasources.TokenDataSource;
import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.repos.ConsultaRepositoryJpaImpl;
import com.example.saudefacilagendamentoservice.infraestructure.persistence.jpa.repos.UsuarioRepositoryJpaImpl;
import com.example.saudefacilagendamentoservice.infraestructure.services.TokenServiceJwtImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public ConsultaDataSource consultaDataSource() {
        return new ConsultaRepositoryJpaImpl();
    }

    @Bean
    public TokenDataSource tokenDataSource() {
        return new TokenServiceJwtImpl();
    }

    @Bean
    public UsuarioDataSource usuarioDataSource() {
        return new UsuarioRepositoryJpaImpl();
    }

}
