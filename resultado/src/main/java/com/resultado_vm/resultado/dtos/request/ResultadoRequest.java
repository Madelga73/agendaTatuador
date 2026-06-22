package com.resultado_vm.resultado.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ResultadoRequest {

    @NotNull(message = "El id del cliente es obligatorio")
    @Positive(message = "El id del cliente debe ser mayor a 0")
    private Long idCliente;

    @NotNull(message = "El id del tatuaje es obligatorio")
    @Positive(message = "El id del tatuaje debe ser mayor a 0")
    private Long idTatuaje;

    @NotNull(message = "La valoracion es obligatoria")
    @Positive(message = "La valoracion debe ser mayor a 0")
    private int valoracion;

    @Size(max = 500, message = "El comentario no puede superar los 500 caracteres")
    private String comentario;
}
