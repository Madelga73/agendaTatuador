package com.ayudante_vm.ayudante.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ayudante")

public class AyudanteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 13)
    private String rut;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 15)
    private String telefono;
}
