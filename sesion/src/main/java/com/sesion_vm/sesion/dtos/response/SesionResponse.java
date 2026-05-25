package com.sesion_vm.sesion.dtos.response;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class SesionResponse {
    private Long id;
    private Long idAyudante;
    private Long idCliente;
    private Long idEstudio;
    private Long idPago;
    private Long idTatuador;
    private Long idTatuaje;
    private LocalDate fechaSesion;
    private LocalTime horaSesion;
    private int duracionHoras;

    private AyudanteResponse ayudante;
    private ClienteResponse cliente;
    private EstudioResponse estudio;
    private PagoResponse pago;
    private TatuadorResponse tatuador;
    private TatuajeResponse tatuaje;
}
