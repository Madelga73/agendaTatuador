package com.pago_vm.pago.controllers;

import com.pago_vm.pago.models.PagoModel;
import com.pago_vm.pago.services.PagoService;

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
@RequestMapping("/api/v1/pagos")
@Tag(
    name = "Pagos",
    description = "Operaciones relacionadas con la gestión de pagos"
)
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    @Operation(
        summary = "Obtener todos los pagos",
        description = "Devuelve un listado con todos los pagos registrados en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PagoModel.class)
        )
    )
    public List<PagoModel> listar() {
        return pagoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener pago por ID",
        description = "Busca un pago utilizando su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pago encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PagoModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Pago no encontrado"
        )
    })
    public ResponseEntity<PagoModel> obtenerPorId(
            @Parameter(
                description = "ID único del pago",
                required = true
            )
            @PathVariable Long id
    ) {
        Optional<PagoModel> pago = pagoService.obtenerPorId(id);

        return pago.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/num_boleta/{numBoleta}")
    @Operation(
        summary = "Obtener pago por número de boleta",
        description = "Busca un pago utilizando su número de boleta"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pago encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PagoModel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Pago no encontrado"
        )
    })
    public ResponseEntity<PagoModel> obtenerPorNumBoleta(
            @Parameter(
                description = "Número de boleta del pago",
                required = true
            )
            @PathVariable String numBoleta
    ) {
        Optional<PagoModel> pago = pagoService.obtenerPorNumBoleta(numBoleta);

        return pago.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "Registrar un pago",
        description = "Crea un nuevo pago en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Pago creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PagoModel.class)
        )
    )
    public ResponseEntity<PagoModel> guardar(
            @RequestBody PagoModel pago
    ) {
        return ResponseEntity.ok(pagoService.guardar(pago));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un pago",
        description = "Elimina un pago del sistema utilizando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Pago eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Pago no encontrado"
        )
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(
                description = "ID del pago a eliminar",
                required = true
            )
            @PathVariable Long id
    ) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}