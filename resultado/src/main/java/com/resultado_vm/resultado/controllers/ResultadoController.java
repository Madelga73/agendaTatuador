package com.resultado_vm.resultado.controllers;

import com.resultado_vm.resultado.services.ResultadoService;
import com.resultado_vm.resultado.dtos.request.ResultadoRequest;
import com.resultado_vm.resultado.dtos.response.ResultadoResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v1/resultados")
@Tag(
    name = "Resultados",
    description = "Operaciones relacionadas con la gestión de resultados y valoraciones"
)
public class ResultadoController {

    private final ResultadoService resultadoService;

    public ResultadoController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los resultados",
        description = "Devuelve un listado con todos los resultados registrados en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ResultadoResponse.class)
        )
    )
    public ResponseEntity<List<ResultadoResponse>> listar() {
        return ResponseEntity.ok(resultadoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener resultado por ID",
        description = "Busca un resultado utilizando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Resultado encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ResultadoResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Resultado no encontrado"
        )
    })
    public ResponseEntity<ResultadoResponse> obtenerPorId(
            @Parameter(
                    description = "ID del resultado",
                    required = true
            )
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(resultadoService.obtenerPorId(id));
    }

    @GetMapping("/cliente/{idCliente}")
    @Operation(
        summary = "Obtener resultados por cliente",
        description = "Lista todos los resultados asociados a un cliente específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ResultadoResponse.class)
        )
    )
    public ResponseEntity<List<ResultadoResponse>> obtenerPorCliente(
            @Parameter(
                    description = "ID del cliente",
                    required = true
            )
            @PathVariable Long idCliente
    ) {
        return ResponseEntity.ok(resultadoService.obtenerPorCliente(idCliente));
    }

    @GetMapping("/tatuaje/{idTatuaje}")
    @Operation(
        summary = "Obtener resultados por tatuaje",
        description = "Lista todos los resultados asociados a un tatuaje específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ResultadoResponse.class)
        )
    )
    public ResponseEntity<List<ResultadoResponse>> obtenerPorTatuaje(
            @Parameter(
                    description = "ID del tatuaje",
                    required = true
            )
            @PathVariable Long idTatuaje
    ) {
        return ResponseEntity.ok(resultadoService.obtenerPorTatuaje(idTatuaje));
    }

    @PostMapping
    @Operation(
        summary = "Crear un resultado",
        description = "Registra una nueva valoración o resultado en el sistema"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Resultado creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ResultadoResponse.class)
        )
    )
    public ResponseEntity<ResultadoResponse> guardar(
            @Valid @RequestBody ResultadoRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultadoService.guardar(request));
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar un resultado",
        description = "Actualiza los datos de un resultado existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Resultado actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ResultadoResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Resultado no encontrado"
        )
    })
    public ResponseEntity<ResultadoResponse> actualizar(
            @Parameter(
                    description = "ID del resultado",
                    required = true
            )
            @PathVariable Long id,
            @Valid @RequestBody ResultadoRequest request
    ) {
        return ResponseEntity.ok(resultadoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un resultado",
        description = "Elimina un resultado utilizando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Resultado eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Resultado no encontrado"
        )
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(
                    description = "ID del resultado a eliminar",
                    required = true
            )
            @PathVariable Long id
    ) {
        resultadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}