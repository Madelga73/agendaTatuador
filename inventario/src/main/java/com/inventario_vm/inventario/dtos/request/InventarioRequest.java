package com.inventario_vm.inventario.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class InventarioRequest {

    @NotNull(message = "El id del tatuador es obligatorio")
    @Positive(message = "El id del tatuador debe ser mayor a 0")
    private Long idTatuador;

    @NotNull(message = "El id del vendedor es obligatorio")
    @Positive(message = "El id del vendedor debe ser mayor a 0")
    private Long idVendedor;

    @NotBlank(message = "El insumo es obligatorio")
    @Size(max = 100, message = "El insumo no puede superar los 100 caracteres")
    private String insumo;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private int cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a 0")
    private int precioUnitario;
}
