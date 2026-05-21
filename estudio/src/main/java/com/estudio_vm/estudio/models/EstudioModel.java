package com.estudio_vm.estudio.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estudio")
public class EstudioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String ubicacion;

}
