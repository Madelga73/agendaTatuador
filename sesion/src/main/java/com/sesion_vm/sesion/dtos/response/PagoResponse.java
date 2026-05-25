package com.sesion_vm.sesion.dtos.response;

import lombok.Data;
@Data

public class PagoResponse {
 
    private Long id;
    private String numBoleta;
    private int montoTotal;
    private int montoInsumos;
    private String metodoPago;
}
