package com.tatuador_vm.tatuador.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tatuador")
public class TatuadorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 13)
    private String rut;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 50)
    private String especialidad;
    
    @Column(nullable = false, length = 15)
    private String telefono;
}
