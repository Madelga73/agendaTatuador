package com.vendedor_vm.vendedor.controllers;

import com.vendedor_vm.vendedor.models.VendedorModel;
import com.vendedor_vm.vendedor.services.VendedorService;

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
@RequestMapping("/api/v1/vendedores")
@Tag(
    name = "Vendedores",
    description = "Operaciones relacionadas con la gestión de vendedores"
)
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los vendedores",
        description = "Devuelve un listado con todos los vendedores registrados en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = VendedorModel.class)
        )
    )
    public List<VendedorModel> listar() {
        return vendedorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener vendedor por ID",
        description = "Busca un vendedor utilizando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Vendedor encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = VendedorModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Vendedor no encontrado"
        )
    })
    public ResponseEntity<VendedorModel> obtenerPorId(
            @Parameter(
                description = "ID único del vendedor",
                required = true
            )
            @PathVariable Long id
    ) {
        Optional<VendedorModel> vendedor = vendedorService.obtenerPorId(id);

        return vendedor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(
        summary = "Obtener vendedor por nombre",
        description = "Busca un vendedor utilizando su nombre"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Vendedor encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = VendedorModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Vendedor no encontrado"
        )
    })
    public ResponseEntity<VendedorModel> obtenerPorNombre(
            @Parameter(
                description = "Nombre del vendedor",
                required = true
            )
            @PathVariable String nombre
    ) {
        Optional<VendedorModel> vendedor = vendedorService.obtenerPorNombre(nombre);

        return vendedor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Registrar un vendedor",
        description = "Crea un nuevo vendedor en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Vendedor creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = VendedorModel.class)
        )
    )
    public ResponseEntity<VendedorModel> guardar(
            @RequestBody VendedorModel vendedor
    ) {
        return ResponseEntity.ok(vendedorService.guardar(vendedor));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un vendedor",
        description = "Elimina un vendedor del sistema utilizando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Vendedor eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Vendedor no encontrado"
        )
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(
                description = "ID del vendedor a eliminar",
                required = true
            )
            @PathVariable Long id
    ) {
        vendedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}