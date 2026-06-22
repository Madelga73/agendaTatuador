package com.resultado_vm.resultado.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoResponse {
    private Long id;
    private Long idCliente;
    private Long idTatuaje;

    private int valoracion;
    private String comentario;

    private ClienteResponse cliente;
    private TatuajeResponse tatuaje;
}
