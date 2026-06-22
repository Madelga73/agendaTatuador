package com.sesion_vm.sesion.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Documentación para Swagger
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "sesion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una sesión de tatuaje agendada")
public class SesionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la sesión", example = "1")
    private Long id;

    @Column(name = "id_ayudante", nullable = false)
    @Schema(description = "Identificador del ayudante asignado a la sesión", example = "2")
    private Long idAyudante;

    @Column(name = "id_cliente", nullable = false)
    @Schema(description = "Identificador del cliente que recibe el servicio", example = "10")
    private Long idCliente;

    @Column(name = "id_estudio", nullable = false)
    @Schema(description = "Identificador del estudio donde se realizará la sesión", example = "3")
    private Long idEstudio;

    @Column(name = "id_pago", nullable = false)
    @Schema(description = "Identificador del pago asociado a la sesión", example = "5")
    private Long idPago;

    @Column(name = "id_tatuador", nullable = false)
    @Schema(description = "Identificador del tatuador asignado a la sesión", example = "7")
    private Long idTatuador;

    @Column(name = "id_tatuaje", nullable = false)
    @Schema(description = "Identificador del tatuaje que se realizará", example = "4")
    private Long idTatuaje;

    @Column(name = "fecha_sesion", nullable = false)
    @Schema(description = "Fecha programada para la sesión", example = "2026-07-15")
    private LocalDate fechaSesion;

    @Column(name = "hora_sesion", nullable = false)
    @Schema(description = "Hora de inicio de la sesión", example = "14:30:00")
    private LocalTime horaSesion;

    @Column(name = "duracion_horas", nullable = false)
    @Schema(description = "Duración estimada de la sesión en horas", example = "4")
    private int duracionHoras;
}