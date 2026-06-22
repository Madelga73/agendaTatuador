package com.cliente_vm.cliente.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Documentación para Swagger
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un cliente registrado en el sistema")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del cliente", example = "1")
    private Long id;

    @Column(nullable = false, unique = true, length = 13)
    @Schema(description = "RUT del cliente", example = "12.345.678-9")
    private String rut;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del cliente", example = "María")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    @Schema(description = "Apellido del cliente", example = "González")
    private String apellido;

    @Column(nullable = false, length = 15)
    @Schema(description = "Teléfono de contacto del cliente", example = "+56912345678")
    private String telefono;
}
