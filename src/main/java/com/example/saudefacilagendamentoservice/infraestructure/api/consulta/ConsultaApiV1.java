package com.example.saudefacilagendamentoservice.infraestructure.api.consulta;

import com.example.saudefacilagendamentoservice.controllers.ConsultaController;
import com.example.saudefacilagendamentoservice.datasources.ConsultaDataSource;
import com.example.saudefacilagendamentoservice.datasources.NotificacaoDataSource;
import com.example.saudefacilagendamentoservice.datasources.TokenDataSource;
import com.example.saudefacilagendamentoservice.datasources.UsuarioDataSource;
import dtos.requests.AlterarConsultaRequest;
import dtos.requests.CriarConsultaRequest;
import dtos.responses.ConsultaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/consultas")
@Tag(name = "API consultas V1", description = "Versão 1 do controlador referente a consultas")
public class ConsultaApiV1 {

    private final ConsultaController consultaController;


    public ConsultaApiV1(TokenDataSource tokenDataSource, UsuarioDataSource usuarioDataSource,
                         ConsultaDataSource consultaDataSource, NotificacaoDataSource notificacaoDataSource) {
        this.consultaController = new ConsultaController(tokenDataSource, usuarioDataSource, consultaDataSource, notificacaoDataSource);
    }

    @Operation(summary = "Consulta consultas de um usuário",
            description = "Requer autenticação e tipo de usuário 'MEDICO' ou 'ENFERMEIRO'",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Consultas consultadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ConsultaResponse.class)))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuário autenticado não é 'MEDICO' ou 'ENFERMEIRO'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/{paciente-id}")
    public ResponseEntity<List<ConsultaResponse>> listarConsultasPorPacienteId(@PathVariable("paciente-id") Long pacienteId,
                                                                               @RequestParam("page") int page,
                                                                               @RequestParam("size") int size) {
        log.info("Consultando consultas do usuário: {}", pacienteId);
        List<ConsultaResponse> restaurantResponseList = consultaController.listarConsultasPorPacienteId(page, size, pacienteId);
        log.info("Retornando {} consultas do usuário: {}", restaurantResponseList.size(), pacienteId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantResponseList);
    }

    @Operation(summary = "Consulta consultas futuras de um usuário",
            description = "Requer autenticação e tipo de usuário 'MEDICO' ou 'ENFERMEIRO'",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Consultas futuras consultadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ConsultaResponse.class)))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuário autenticado não é 'MEDICO' ou 'ENFERMEIRO'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/futuras/{paciente-id}")
    public ResponseEntity<List<ConsultaResponse>> listarConsultasFuturasPorPacienteId(@PathVariable("paciente-id") Long pacienteId,
                                                                               @RequestParam("page") int page,
                                                                               @RequestParam("size") int size) {
        log.info("Consultando consultas futuras do usuário: {}", pacienteId);
        List<ConsultaResponse> restaurantResponseList = consultaController.listarConsultasFuturasPorPacienteId(page, size, pacienteId);
        log.info("Retornando {} consultas futuras do usuário: {}", restaurantResponseList.size(), pacienteId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantResponseList);
    }

    @Operation(summary = "Consulta consultas do seu usuário",
            description = "Requer autenticação e tipo de usuário 'PACIENTE'",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Consultas do seu usuário consultadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ConsultaResponse.class)))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuário autenticado não é 'PACIENTE'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping
    public ResponseEntity<List<ConsultaResponse>> listarConsultasPorToken(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                                                          @RequestParam("page") int page,
                                                                          @RequestParam("size") int size) {
        log.info("Consultando consultas do próprio usuário");
        List<ConsultaResponse> restaurantResponseList = consultaController.listarConsultasPorToken(page, size, token);
        log.info("Retornando {} consultas futuras do próprio usuário", restaurantResponseList.size());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantResponseList);
    }

    @Operation(summary = "Cria uma consulta",
            description = "Requer autenticação e tipo de usuário 'MEDICO' ou 'ENFERMEIRO'")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Consulta criada com sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para os atributos da consulta a ser criada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Paciente ou médico não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<Void> criarConsulta(@RequestBody @Valid CriarConsultaRequest request) {
        log.info("Criando consulta para paciente: {}", request.paciente());
        consultaController.criarConsulta(request);
        log.info("Consulta criada para o paciente: {}", request.paciente());

        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Altera uma consulta",
            description = "Requer autenticação e tipo de usuário 'MEDICO' ou 'ENFERMEIRO'")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Consulta alterada com sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para os atributos da consulta",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Paciente ou médico não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping("/{consulta-id}")
    public ResponseEntity<Void> alterarConsulta(@PathVariable("consulta-id") Long consultaId,
                                                         @RequestBody @Valid AlterarConsultaRequest request) {
        log.info("Alterando consulta: {}", consultaId);
        consultaController.alterarConsulta(consultaId, request);
        log.info("Consulta {} alterada", consultaId);

        return ResponseEntity
                .status(HttpStatus.OK).build();
    }
}
