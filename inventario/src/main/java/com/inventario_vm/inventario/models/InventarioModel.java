package com.inventario_vm.inventario.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Documentación para Swagger
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "inventario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un insumo registrado en el inventario")
public class InventarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del registro de inventario", example = "1")
    private Long id;

    @Column(name = "id_tatuador", nullable = false)
    @Schema(description = "Identificador del tatuador asociado al insumo", example = "5")
    private Long idTatuador;

    @Column(name = "id_vendedor", nullable = false)
    @Schema(description = "Identificador del vendedor asociado al insumo", example = "3")
    private Long idVendedor;

    @Column(nullable = false, length = 100)
    @Schema(description = "Nombre del insumo registrado en inventario", example = "Tinta negra premium")
    private String insumo;

    @Column(nullable = false)
    @Schema(description = "Cantidad disponible del insumo", example = "20")
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false)
    @Schema(description = "Precio unitario del insumo", example = "15000")
    private int precioUnitario;
}