package com.inventario_vm.inventario.dtos.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventarioResponse {
    private Long id;
    private Long idTatuador;
    private Long idVendedor;
    private String insumo;
    private int cantidad;
    private int precioUnitario;

    private TatuadorResponse tatuador;
    private VendedorResponse vendedor;
}
