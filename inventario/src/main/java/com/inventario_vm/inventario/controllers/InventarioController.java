package com.inventario_vm.inventario.controllers;

import com.inventario_vm.inventario.services.InventarioService;
import com.inventario_vm.inventario.dtos.request.InventarioRequest;
import com.inventario_vm.inventario.dtos.response.InventarioResponse;

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
@RequestMapping("/api/v1/inventarios")
@Tag(
    name = "Inventarios",
    description = "Operaciones relacionadas con la gestión de inventarios"
)
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los inventarios",
        description = "Devuelve un listado con todos los registros de inventario"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = InventarioResponse.class)
        )
    )
    public ResponseEntity<List<InventarioResponse>> listar() {
        return ResponseEntity.ok(inventarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener inventario por ID",
        description = "Busca un registro de inventario utilizando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Inventario encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = InventarioResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Inventario no encontrado"
        )
    })
    public ResponseEntity<InventarioResponse> obtenerPorId(
            @Parameter(description = "ID del inventario", required = true)
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(inventarioService.obtenerPorId(id));
    }

    @GetMapping("/tatuador/{idTatuador}")
    @Operation(
        summary = "Obtener inventarios por tatuador",
        description = "Lista todos los inventarios asociados a un tatuador específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = InventarioResponse.class)
        )
    )
    public ResponseEntity<List<InventarioResponse>> obtenerPorTatuador(
            @Parameter(description = "ID del tatuador", required = true)
            @PathVariable Long idTatuador
    ) {
        return ResponseEntity.ok(inventarioService.obtenerPorTatuador(idTatuador));
    }

    @GetMapping("/vendedor/{idVendedor}")
    @Operation(
        summary = "Obtener inventarios por vendedor",
        description = "Lista todos los inventarios asociados a un vendedor específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = InventarioResponse.class)
        )
    )
    public ResponseEntity<List<InventarioResponse>> obtenerPorVendedor(
            @Parameter(description = "ID del vendedor", required = true)
            @PathVariable Long idVendedor
    ) {
        return ResponseEntity.ok(inventarioService.obtenerPorVendedor(idVendedor));
    }

    @PostMapping
    @Operation(
        summary = "Crear un inventario",
        description = "Registra un nuevo inventario en el sistema"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Inventario creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = InventarioResponse.class)
        )
    )
    public ResponseEntity<InventarioResponse> guardar(
            @Valid @RequestBody InventarioRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventarioService.guardar(request));
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar un inventario",
        description = "Actualiza los datos de un inventario existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Inventario actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = InventarioResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Inventario no encontrado"
        )
    })
    public ResponseEntity<InventarioResponse> actualizar(
            @Parameter(description = "ID del inventario", required = true)
            @PathVariable Long id,
            @Valid @RequestBody InventarioRequest request
    ) {
        return ResponseEntity.ok(inventarioService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un inventario",
        description = "Elimina un registro de inventario utilizando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Inventario eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Inventario no encontrado"
        )
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del inventario a eliminar", required = true)
            @PathVariable Long id
    ) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}