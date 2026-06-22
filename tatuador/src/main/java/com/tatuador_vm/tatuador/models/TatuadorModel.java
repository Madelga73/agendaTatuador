package com.tatuador_vm.tatuador.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Documentación para Swagger
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tatuador")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un tatuador registrado en el sistema")
public class TatuadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del tatuador", example = "1")
    private Long id;

    @Column(nullable = false, unique = true, length = 13)
    @Schema(description = "RUT del tatuador", example = "12.345.678-9")
    private String rut;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del tatuador", example = "Sebastián")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    @Schema(description = "Apellido del tatuador", example = "González")
    private String apellido;

    @Column(nullable = false, length = 50)
    @Schema(description = "Especialidad del tatuador", example = "Realismo")
    private String especialidad;

    @Column(nullable = false, length = 15)
    @Schema(description = "Teléfono de contacto del tatuador", example = "+56912345678")
    private String telefono;
}
