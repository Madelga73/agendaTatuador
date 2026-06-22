package com.ayudante_vm.ayudante.controllers;

import com.ayudante_vm.ayudante.models.AyudanteModel;
import com.ayudante_vm.ayudante.services.AyudanteService;

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
@RequestMapping("/api/v1/ayudantes")
@Tag(
    name = "Ayudantes",
    description = "Operaciones relacionadas con la gestión de ayudantes"
)
public class AyudanteController {

    private final AyudanteService ayudanteService;

    public AyudanteController(AyudanteService ayudanteService) {
        this.ayudanteService = ayudanteService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los ayudantes",
        description = "Devuelve un listado con todos los ayudantes registrados en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AyudanteModel.class)
        )
    )
    public List<AyudanteModel> listar() {
        return ayudanteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener ayudante por ID",
        description = "Busca un ayudante utilizando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Ayudante encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AyudanteModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Ayudante no encontrado"
        )
    })
    public ResponseEntity<AyudanteModel> obtenerPorId(
        @Parameter(
            description = "ID único del ayudante",
            required = true
        )
        @PathVariable Long id
    ) {
        Optional<AyudanteModel> ayudante = ayudanteService.obtenerPorId(id);

        return ayudante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    @Operation(
        summary = "Obtener ayudante por RUT",
        description = "Busca un ayudante utilizando su RUT"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Ayudante encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AyudanteModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Ayudante no encontrado"
        )
    })
    public ResponseEntity<AyudanteModel> obtenerPorRut(
        @Parameter(
            description = "RUT del ayudante",
            required = true
        )
        @PathVariable String rut
    ) {
        Optional<AyudanteModel> ayudante = ayudanteService.obtenerPorRut(rut);

        return ayudante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Registrar un ayudante",
        description = "Crea un nuevo ayudante en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Ayudante creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AyudanteModel.class)
        )
    )
    public ResponseEntity<AyudanteModel> guardar(
        @RequestBody AyudanteModel ayudante
    ) {
        return ResponseEntity.ok(ayudanteService.guardar(ayudante));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un ayudante",
        description = "Elimina un ayudante del sistema utilizando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Ayudante eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Ayudante no encontrado"
        )
    })
    public ResponseEntity<Void> eliminar(
        @Parameter(
            description = "ID del ayudante a eliminar",
            required = true
        )
        @PathVariable Long id
    ) {
        ayudanteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
