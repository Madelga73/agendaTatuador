package com.estudio_vm.estudio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Documentación para Swagger
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "estudio")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un estudio jurídico registrado en el sistema")
public class EstudioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del estudio", example = "1")
    private Long id;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del estudio jurídico", example = "Pérez & Asociados")
    private String nombre;

    @Column(nullable = false, length = 100)
    @Schema(description = "Ubicación o dirección del estudio", example = "Av. Libertador Bernardo O'Higgins 1234, Santiago")
    private String ubicacion;
}