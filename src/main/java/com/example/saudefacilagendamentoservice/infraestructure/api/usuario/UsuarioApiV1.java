package com.example.saudefacilagendamentoservice.infraestructure.api.usuario;

import com.example.saudefacilagendamentoservice.controllers.UsuarioController;
import com.example.saudefacilagendamentoservice.datasources.TokenDataSource;
import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import com.example.saudefacilagendamentoservice.dtos.responses.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/usuarios")
@Tag(name = "User API V1", description = "Versão 1 do controlador referente a usuários")
public class UsuarioApiV1 {

    private final UsuarioController usuarioController;

    public UsuarioApiV1(TokenDataSource tokenDataSource, UsuarioDataSource usuarioDataSource) {
        this.usuarioController = new UsuarioController(tokenDataSource, usuarioDataSource);
    }

    @Operation(summary = "Gera token de acesso",
            description = "Requer autenticação",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Token gerado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/gerar-token")
    public ResponseEntity<TokenResponse> generateToken(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Usuário {} gerando token", userDetails.getUsername());
        TokenResponse response = usuarioController.gerarToken(String.valueOf(userDetails.getAuthorities().stream().findFirst().get()), userDetails.getUsername());
        log.info("Usuário {} gerou token", userDetails.getUsername());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
