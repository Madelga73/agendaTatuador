package com.tatuaje_vm.tatuaje.controllers;

import com.tatuaje_vm.tatuaje.models.TatuajeModel;
import com.tatuaje_vm.tatuaje.services.TatuajeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tatuajes")
@Tag(
    name = "Tatuajes",
    description = "Operaciones relacionadas con la gestión de tatuajes"
)
public class TatuajeController {

    private final TatuajeService tatuajeService;

    public TatuajeController(TatuajeService tatuajeService) {
        this.tatuajeService = tatuajeService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los tatuajes",
        description = "Devuelve un listado con todos los tatuajes registrados en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TatuajeModel.class)
        )
    )
    public List<TatuajeModel> listar() {
        return tatuajeService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener tatuaje por ID",
        description = "Busca un tatuaje utilizando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tatuaje encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = TatuajeModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tatuaje no encontrado"
        )
    })
    public ResponseEntity<TatuajeModel> obtenerPorId(
            @Parameter(
                description = "ID único del tatuaje",
                required = true
            )
            @PathVariable Long id
    ) {
        Optional<TatuajeModel> tatuaje = tatuajeService.obtenerPorId(id);

        return tatuaje.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/descripcion/{descripcion}")
    @Operation(
        summary = "Obtener tatuaje por descripción",
        description = "Busca un tatuaje utilizando su descripción"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tatuaje encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = TatuajeModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tatuaje no encontrado"
        )
    })
    public ResponseEntity<TatuajeModel> obtenerPorDescripcion(
            @Parameter(
                description = "Descripción del tatuaje",
                required = true
            )
            @PathVariable String descripcion
    ) {
        Optional<TatuajeModel> tatuaje = tatuajeService.obtenerPorDescripcion(descripcion);

        return tatuaje.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Registrar un tatuaje",
        description = "Crea un nuevo tatuaje en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Tatuaje creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TatuajeModel.class)
        )
    )
    public ResponseEntity<TatuajeModel> guardar(
            @RequestBody TatuajeModel tatuaje
    ) {
        return ResponseEntity.ok(tatuajeService.guardar(tatuaje));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un tatuaje",
        description = "Elimina un tatuaje del sistema utilizando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Tatuaje eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tatuaje no encontrado"
        )
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(
                description = "ID del tatuaje a eliminar",
                required = true
            )
            @PathVariable Long id
    ) {
        tatuajeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}