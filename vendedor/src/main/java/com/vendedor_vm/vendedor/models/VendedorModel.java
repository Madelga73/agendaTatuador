package com.vendedor_vm.vendedor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Documentación para Swagger
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "vendedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un vendedor registrado en el sistema")
public class VendedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del vendedor", example = "1")
    private Long id;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del vendedor", example = "Carlos Pérez")
    private String nombre;

    @Column(nullable = false, length = 100)
    @Schema(
        description = "Ubicación o sucursal donde trabaja el vendedor",
        example = "Santiago Centro"
    )
    private String ubicacion;
}