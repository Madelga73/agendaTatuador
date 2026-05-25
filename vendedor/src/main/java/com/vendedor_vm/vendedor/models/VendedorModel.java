package com.vendedor_vm.vendedor.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vendedor")
public class VendedorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String ubicacion;
}
