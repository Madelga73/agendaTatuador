package com.pago_vm.pago.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Documentación para Swagger
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un pago realizado por un cliente")
public class PagoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del pago", example = "1")
    private Long id;

    @Column(name = "num_boleta", nullable = false, length = 50)
    @Schema(description = "Número de boleta asociado al pago", example = "BOL-2026-001")
    private String numBoleta;

    @Column(name = "monto_total", nullable = false)
    @Schema(description = "Monto total del pago", example = "150000")
    private int montoTotal;

    @Column(name = "monto_insumos", nullable = false)
    @Schema(description = "Monto correspondiente a insumos utilizados", example = "25000")
    private int montoInsumos;

    @Column(name = "metodo_pago", nullable = false, length = 50)
    @Schema(description = "Método de pago utilizado", example = "Tarjeta de Crédito")
    private String metodoPago;
}
