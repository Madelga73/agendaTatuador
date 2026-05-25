package com.sesion_vm.sesion.models;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "sesion")
public class SesionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "id_ayudante", nullable = false)
    private Long idAyudante;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "id_estudio", nullable = false)
    private Long idEstudio;

    @Column(name = "id_pago", nullable = false)
    private Long idPago;

    @Column(name = "id_tatuador", nullable = false)
    private Long idTatuador;
    
    @Column(name = "id_tatuaje", nullable = false)
    private Long idTatuaje;

    @Column(name = "fecha_sesion", nullable = false)
    private LocalDate fechaSesion;
 
    @Column(name = "hora_sesion", nullable = false)
    private LocalTime horaSesion;

    @Column(name = "duracion_horas",nullable = false)
    private int duracionHoras;
}
