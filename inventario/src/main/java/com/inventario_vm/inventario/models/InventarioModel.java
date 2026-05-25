package com.inventario_vm.inventario.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inventario")
public class InventarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_tatuador", nullable = false)
    private Long idTatuador;

    @Column(name = "id_vendedor", nullable = false)
    private Long idVendedor;

    @Column(nullable = false, length = 100)
    private String insumo;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private int precioUnitario;
}
