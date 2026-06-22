package com.estudio_vm.estudio.controllers;

import com.estudio_vm.estudio.models.EstudioModel;
import com.estudio_vm.estudio.services.EstudioService;

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
@RequestMapping("/api/v1/estudios")
@Tag(
    name = "Estudios",
    description = "Operaciones relacionadas con la gestión de estudios"
)
public class EstudioController {

    private final EstudioService estudioService;

    public EstudioController(EstudioService estudioService) {
        this.estudioService = estudioService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los estudios",
        description = "Devuelve un listado con todos los estudios registrados en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = EstudioModel.class)
        )
    )
    public List<EstudioModel> listar() {
        return estudioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener estudio por ID",
        description = "Busca un estudio utilizando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estudio encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstudioModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Estudio no encontrado"
        )
    })
    public ResponseEntity<EstudioModel> obtenerPorId(
        @Parameter(
            description = "ID único del estudio",
            required = true
        )
        @PathVariable Long id
    ) {
        Optional<EstudioModel> estudio = estudioService.obtenerPorId(id);

        return estudio.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(
        summary = "Obtener estudio por nombre",
        description = "Busca un estudio utilizando su nombre"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estudio encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstudioModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Estudio no encontrado"
        )
    })
    public ResponseEntity<EstudioModel> obtenerPorNombre(
        @Parameter(
            description = "Nombre del estudio",
            required = true
        )
        @PathVariable String nombre
    ) {
        Optional<EstudioModel> estudio = estudioService.obtenerPorNombre(nombre);

        return estudio.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Registrar un estudio",
        description = "Crea un nuevo estudio en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Estudio creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = EstudioModel.class)
        )
    )
    public ResponseEntity<EstudioModel> guardar(
        @RequestBody EstudioModel estudio
    ) {
        return ResponseEntity.ok(estudioService.guardar(estudio));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un estudio",
        description = "Elimina un estudio del sistema utilizando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Estudio eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Estudio no encontrado"
        )
    })
    public ResponseEntity<Void> eliminar(
        @Parameter(
            description = "ID del estudio a eliminar",
            required = true
        )
        @PathVariable Long id
    ) {
        estudioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}