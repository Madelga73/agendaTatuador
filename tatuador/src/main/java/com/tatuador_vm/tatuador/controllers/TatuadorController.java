package com.tatuador_vm.tatuador.controllers;

import com.tatuador_vm.tatuador.models.TatuadorModel;
import com.tatuador_vm.tatuador.services.TatuadorService;

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
@RequestMapping("/api/v1/tatuadores")
@Tag(
    name = "Tatuadores",
    description = "Operaciones relacionadas con la gestión de tatuadores"
)
public class TatuadorController {

    private final TatuadorService tatuadorService;

    public TatuadorController(TatuadorService tatuadorService) {
        this.tatuadorService = tatuadorService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los tatuadores",
        description = "Devuelve un listado con todos los tatuadores registrados en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TatuadorModel.class)
        )
    )
    public List<TatuadorModel> listar() {
        return tatuadorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener tatuador por ID",
        description = "Busca un tatuador utilizando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tatuador encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = TatuadorModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tatuador no encontrado"
        )
    })
    public ResponseEntity<TatuadorModel> obtenerPorId(
            @Parameter(
                description = "ID único del tatuador",
                required = true
            )
            @PathVariable Long id
    ) {
        Optional<TatuadorModel> tatuador = tatuadorService.obtenerPorId(id);

        return tatuador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    @Operation(
        summary = "Obtener tatuador por RUT",
        description = "Busca un tatuador utilizando su RUT"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tatuador encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = TatuadorModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tatuador no encontrado"
        )
    })
    public ResponseEntity<TatuadorModel> obtenerPorRut(
            @Parameter(
                description = "RUT del tatuador",
                required = true
            )
            @PathVariable String rut
    ) {
        Optional<TatuadorModel> tatuador = tatuadorService.obtenerPorRut(rut);

        return tatuador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Registrar un tatuador",
        description = "Crea un nuevo tatuador en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Tatuador creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TatuadorModel.class)
        )
    )
    public ResponseEntity<TatuadorModel> guardar(
            @RequestBody TatuadorModel tatuador
    ) {
        return ResponseEntity.ok(tatuadorService.guardar(tatuador));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un tatuador",
        description = "Elimina un tatuador del sistema utilizando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Tatuador eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tatuador no encontrado"
        )
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(
                description = "ID del tatuador a eliminar",
                required = true
            )
            @PathVariable Long id
    ) {
        tatuadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}