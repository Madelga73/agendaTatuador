package com.sesion_vm.sesion.controllers;

import com.sesion_vm.sesion.Services.SesionService;
import com.sesion_vm.sesion.dtos.request.SesionRequest;
import com.sesion_vm.sesion.dtos.response.SesionResponse;

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
@RequestMapping("/api/v1/sesiones")
@Tag(
    name = "Sesiones",
    description = "Operaciones relacionadas con la gestión de sesiones"
)
public class SesionController {

    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todas las sesiones",
        description = "Devuelve un listado con todas las sesiones registradas en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = SesionResponse.class)
        )
    )
    public ResponseEntity<List<SesionResponse>> listar() {
        return ResponseEntity.ok(sesionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener sesión por ID",
        description = "Busca una sesión utilizando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sesión encontrada",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SesionResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sesión no encontrada"
        )
    })
    public ResponseEntity<SesionResponse> obtenerPorId(
            @Parameter(description = "ID de la sesión", required = true)
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorId(id));
    }

    @GetMapping("/ayudante/{idAyudante}")
    @Operation(
        summary = "Obtener sesiones por ayudante",
        description = "Lista todas las sesiones asociadas a un ayudante específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = SesionResponse.class)
        )
    )
    public ResponseEntity<List<SesionResponse>> obtenerPorAyudante(
            @Parameter(description = "ID del ayudante", required = true)
            @PathVariable Long idAyudante
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorAyudante(idAyudante));
    }

    @GetMapping("/cliente/{idCliente}")
    @Operation(
        summary = "Obtener sesiones por cliente",
        description = "Lista todas las sesiones asociadas a un cliente específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = SesionResponse.class)
        )
    )
    public ResponseEntity<List<SesionResponse>> obtenerPorCliente(
            @Parameter(description = "ID del cliente", required = true)
            @PathVariable Long idCliente
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorCliente(idCliente));
    }

    @GetMapping("/estudio/{idEstudio}")
    @Operation(
        summary = "Obtener sesiones por estudio",
        description = "Lista todas las sesiones asociadas a un estudio específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = SesionResponse.class)
        )
    )
    public ResponseEntity<List<SesionResponse>> obtenerPorEstudio(
            @Parameter(description = "ID del estudio", required = true)
            @PathVariable Long idEstudio
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorEstudio(idEstudio));
    }

    @GetMapping("/pago/{idPago}")
    @Operation(
        summary = "Obtener sesiones por pago",
        description = "Lista todas las sesiones asociadas a un pago específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = SesionResponse.class)
        )
    )
    public ResponseEntity<List<SesionResponse>> obtenerPorPago(
            @Parameter(description = "ID del pago", required = true)
            @PathVariable Long idPago
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorPago(idPago));
    }

    @GetMapping("/tatuador/{idTatuador}")
    @Operation(
        summary = "Obtener sesiones por tatuador",
        description = "Lista todas las sesiones asociadas a un tatuador específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = SesionResponse.class)
        )
    )
    public ResponseEntity<List<SesionResponse>> obtenerPorTatuador(
            @Parameter(description = "ID del tatuador", required = true)
            @PathVariable Long idTatuador
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorTatuador(idTatuador));
    }

    @GetMapping("/tatuaje/{idTatuaje}")
    @Operation(
        summary = "Obtener sesiones por tatuaje",
        description = "Lista todas las sesiones asociadas a un tatuaje específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = SesionResponse.class)
        )
    )
    public ResponseEntity<List<SesionResponse>> obtenerPorTatuaje(
            @Parameter(description = "ID del tatuaje", required = true)
            @PathVariable Long idTatuaje
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorTatuaje(idTatuaje));
    }

    @PostMapping
    @Operation(
        summary = "Crear una sesión",
        description = "Registra una nueva sesión en el sistema"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Sesión creada exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = SesionResponse.class)
        )
    )
    public ResponseEntity<SesionResponse> guardar(
            @Valid @RequestBody SesionRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sesionService.guardar(request));
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar una sesión",
        description = "Actualiza los datos de una sesión existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sesión actualizada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SesionResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sesión no encontrada"
        )
    })
    public ResponseEntity<SesionResponse> actualizar(
            @Parameter(description = "ID de la sesión", required = true)
            @PathVariable Long id,
            @Valid @RequestBody SesionRequest request
    ) {
        return ResponseEntity.ok(sesionService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar una sesión",
        description = "Elimina una sesión utilizando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Sesión eliminada exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sesión no encontrada"
        )
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la sesión a eliminar", required = true)
            @PathVariable Long id
    ) {
        sesionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}