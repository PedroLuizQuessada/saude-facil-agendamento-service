package com.example.saudefacilagendamentoservice.infraestructure.api.usuario;

import com.example.saudefacilagendamentoservice.controllers.UsuarioController;
import com.example.saudefacilagendamentoservice.datasources.TokenDataSource;
import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import com.example.saudefacilagendamentoservice.dtos.requests.CriarUsuarioRequest;
import com.example.saudefacilagendamentoservice.dtos.responses.TokenResponse;
import com.example.saudefacilagendamentoservice.dtos.responses.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/usuarios")
@Tag(name = "API usuários V1", description = "Versão 1 do controlador referente a usuários")
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
    public ResponseEntity<TokenResponse> gerarToken(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Usuário {} gerando token", userDetails.getUsername());
        TokenResponse response = usuarioController.gerarToken(String.valueOf(userDetails.getAuthorities().stream().findFirst().get()), userDetails.getUsername());
        log.info("Usuário {} gerou token", userDetails.getUsername());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Cria um usuário",
            description = "Endpoint liberado para usuários não autenticados")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para os atributos do usuário a ser criado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Tipo de usuário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody @Valid CriarUsuarioRequest request) {
        log.info("Criando usuário: {}", request.email());
        usuarioController.criarUsuario(request);
        log.info("Usuário criado: {}", request.email());

        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }
}
