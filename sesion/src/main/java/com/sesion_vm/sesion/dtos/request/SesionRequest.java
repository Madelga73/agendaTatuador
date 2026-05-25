package com.sesion_vm.sesion.dtos.request;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SesionRequest {
    
    @NotNull(message = "El id del ayudante es obligatorio")
    @Positive(message = "El id del ayudante debe ser mayor a 0")
    private Long idAyudante;

    @NotNull(message = "El id del cliente es obligatorio")
    @Positive(message = "El id del cliente debe ser mayor a 0")
    private Long idCliente;

    @NotNull(message = "El id del estudio es obligatorio")
    @Positive(message = "El id del estudio debe ser mayor a 0")
    private Long idEstudio;

    @NotNull(message = "El id del pago es obligatorio")
    @Positive(message = "El id del pago debe ser mayor a 0")
    private Long idPago;

    @NotNull(message = "El id del tatuador es obligatorio")
    @Positive(message = "El id del tatuador debe ser mayor a 0")
    private Long idTatuador;
    
    @NotNull(message = "El id del tatuaje es obligatorio")
    @Positive(message = "El id del tatuaje debe ser mayor a 0")
    private Long idTatuaje;

    @NotNull(message = "La fecha de la sesion es obligatoria")
    private LocalDate fechaSesion;
 
    @NotNull(message = "La hora de la sesion es obligatoria")
    private LocalTime horaSesion;

    @NotNull(message = "La duracion es obligatoria")
    @Positive(message = "La duracion debe ser mayor a 0")
    private int duracionHoras;
}
