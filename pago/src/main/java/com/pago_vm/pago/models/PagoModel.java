package com.pago_vm.pago.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pago")
public class PagoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_boleta",nullable = false, length = 50)
    private String numBoleta;

    @Column(name = "monto_total", nullable = false)
    private int montoTotal;

    @Column(name = "monto_insumos",nullable = false)
    private int montoInsumos;

    @Column(name = "metodo_pago",nullable = false, length = 50)
    private String metodoPago;
}
