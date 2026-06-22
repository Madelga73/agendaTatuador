package com.ayudante_vm.ayudante.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Documentación para Swagger
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "ayudante")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un ayudante registrado en el sistema")
public class AyudanteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del ayudante", example = "1")
    private Long id;

    @Column(nullable = false, unique = true, length = 13)
    @Schema(description = "RUT del ayudante", example = "12.345.678-9")
    private String rut;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del ayudante", example = "Juan")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    @Schema(description = "Apellido del ayudante", example = "Pérez")
    private String apellido;

    @Column(nullable = false, length = 15)
    @Schema(description = "Teléfono de contacto del ayudante", example = "+56912345678")
    private String telefono;
}