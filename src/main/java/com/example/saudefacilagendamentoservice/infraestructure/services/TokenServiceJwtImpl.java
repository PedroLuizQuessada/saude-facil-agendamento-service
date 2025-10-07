package com.example.saudefacilagendamentoservice.infraestructure.services;

import com.example.saudefacilagendamentoservice.datasources.TokenDataSource;
import com.example.saudefacilagendamentoservice.infraestructure.exceptions.InvalidJwtException;
import dtos.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Profile("jwt")
public class TokenServiceJwtImpl implements TokenDataSource {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${jwt.token.expiration-time}")
    private Long expirationTime;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public TokenDto gerarToken(String tipoUsuario, String email) {
        Instant now = Instant.now();

        var claims = JwtClaimsSet.builder()
                .issuer(applicationName)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirationTime))
                .claim("authorities", tipoUsuario)
                .subject(email)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new TokenDto(token);
    }

    @Override
    public String getEmail(String token) {
        Jwt jwt = decodeToken(token);
        return jwt.getSubject();
    }

    private Jwt decodeToken(String token) {
        try {
            return jwtDecoder.decode(token.replace("Bearer ", ""));
        }
        catch (Exception e) {
            throw new InvalidJwtException();
        }
    }
}
