package com.cliente_vm.cliente.controllers;

import com.cliente_vm.cliente.models.ClienteModel;
import com.cliente_vm.cliente.services.ClienteService;

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
@RequestMapping("/api/v1/clientes")
@Tag(
    name = "Clientes",
    description = "Operaciones relacionadas con la gestión de clientes"
)
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los clientes",
        description = "Devuelve un listado con todos los clientes registrados en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ClienteModel.class)
        )
    )
    public List<ClienteModel> listar() {
        return clienteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener cliente por ID",
        description = "Busca un cliente utilizando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Cliente encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ClienteModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado"
        )
    })
    public ResponseEntity<ClienteModel> obtenerPorId(
        @Parameter(
            description = "ID único del cliente",
            required = true
        )
        @PathVariable Long id
    ) {
        Optional<ClienteModel> cliente = clienteService.obtenerPorId(id);

        return cliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    @Operation(
        summary = "Obtener cliente por RUT",
        description = "Busca un cliente utilizando su RUT"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Cliente encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ClienteModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado"
        )
    })
    public ResponseEntity<ClienteModel> obtenerPorRut(
        @Parameter(
            description = "RUT del cliente",
            required = true
        )
        @PathVariable String rut
    ) {
        Optional<ClienteModel> cliente = clienteService.obtenerPorRut(rut);

        return cliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Registrar un cliente",
        description = "Crea un nuevo cliente en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Cliente creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ClienteModel.class)
        )
    )
    public ResponseEntity<ClienteModel> guardar(
        @RequestBody ClienteModel cliente
    ) {
        return ResponseEntity.ok(clienteService.guardar(cliente));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un cliente",
        description = "Elimina un cliente del sistema utilizando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Cliente eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado"
        )
    })
    public ResponseEntity<Void> eliminar(
        @Parameter(
            description = "ID del cliente a eliminar",
            required = true
        )
        @PathVariable Long id
    ) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
