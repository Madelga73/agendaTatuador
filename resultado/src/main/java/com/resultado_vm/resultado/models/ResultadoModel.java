package com.resultado_vm.resultado.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Documentación para Swagger
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "resultado")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa la valoración y comentario de un cliente sobre un tatuaje realizado")
public class ResultadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del resultado", example = "1")
    private Long id;

    @Column(name = "id_cliente", nullable = false)
    @Schema(description = "Identificador del cliente que realizó la valoración", example = "10")
    private Long idCliente;

    @Column(name = "id_tatuaje", nullable = false)
    @Schema(description = "Identificador del tatuaje evaluado", example = "5")
    private Long idTatuaje;

    @Column(nullable = false)
    @Schema(description = "Valoración otorgada por el cliente", example = "5")
    private int valoracion;

    @Column(length = 500)
    @Schema(
        description = "Comentario realizado por el cliente sobre el tatuaje",
        example = "Excelente trabajo, muy detallado y profesional."
    )
    private String comentario;
}
