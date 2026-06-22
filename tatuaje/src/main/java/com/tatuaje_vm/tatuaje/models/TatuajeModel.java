package com.tatuaje_vm.tatuaje.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Documentación para Swagger
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tatuaje")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un tatuaje solicitado o realizado por un cliente")
public class TatuajeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del tatuaje", example = "1")
    private Long id;

    @Column(nullable = false, length = 500)
    @Schema(
        description = "Descripción detallada del tatuaje",
        example = "Dragón japonés en escala de grises con detalles florales"
    )
    private String descripcion;

    @Column(nullable = false, length = 50)
    @Schema(
        description = "Zona del cuerpo donde se realizará el tatuaje",
        example = "Brazo"
    )
    private String zona;

    @Column(nullable = false, length = 50)
    @Schema(
        description = "Estilo artístico del tatuaje",
        example = "Realismo"
    )
    private String estilo;
}