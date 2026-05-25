package com.resultado_vm.resultado.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "resultado")
public class ResultadoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "id_tatuaje", nullable = false)
    private Long idTatuaje;

    @Column(nullable = false)
    private int valoracion;

    @Column(length = 500)
    private String comentario;
}
