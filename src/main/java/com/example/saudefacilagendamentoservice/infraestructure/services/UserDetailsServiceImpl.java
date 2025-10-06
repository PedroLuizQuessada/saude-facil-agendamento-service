package com.example.saudefacilagendamentoservice.infraestructure.services;

import com.example.saudefacilagendamentoservice.controllers.LoginController;
import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import com.example.saudefacilagendamentoservice.dtos.responses.LoginResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginController loginController;

    public UserDetailsServiceImpl(UsuarioDataSource usuarioDataSource) {
        this.loginController = new LoginController(usuarioDataSource);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginResponse loginResponse = loginController.login(username);
        return new org.springframework.security.core.userdetails.User(loginResponse.email(), loginResponse.senha(),
                List.of(new SimpleGrantedAuthority(String.valueOf(loginResponse.tipo()))));
    }
}
