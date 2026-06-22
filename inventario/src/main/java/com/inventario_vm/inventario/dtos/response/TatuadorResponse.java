package com.inventario_vm.inventario.dtos.response;
import lombok.Data;
@Data
public class TatuadorResponse {
    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
}
