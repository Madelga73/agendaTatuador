package com.tatuaje_vm.tatuaje.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tatuaje")
public class TatuajeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false, length = 50)
    private String zona;

    @Column(nullable = false, length = 50)
    private String estilo;
}
