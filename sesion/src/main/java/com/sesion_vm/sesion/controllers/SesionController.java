package com.sesion_vm.sesion.controllers;

import com.sesion_vm.sesion.Services.SesionService;

import com.sesion_vm.sesion.dtos.request.SesionRequest;
import com.sesion_vm.sesion.dtos.response.SesionResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

// Expone los endpoints REST del microservicio de sesiones
@RestController
@RequestMapping("/api/v1/sesiones")
public class SesionController {

    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    // GET /api/v1/sesiones
    // Lista todas las sesiones
    @GetMapping
    public ResponseEntity<List<SesionResponse>> listar() {
        return ResponseEntity.ok(sesionService.obtenerTodas());
    }

    // GET /api/v1/sesiones/{id}
    // Busca una sesion por su ID
    @GetMapping("/{id}")
    public ResponseEntity<SesionResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sesionService.obtenerPorId(id));
    }

    // GET /api/v1/sesiones/ayudante/{idAyudante}
    // Lista las sesiones de un ayudante específico
    @GetMapping("/ayudante/{idAyudante}")
    public ResponseEntity<List<SesionResponse>> obtenerPorAyudante(
            @PathVariable Long idAyudante
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorAyudante(idAyudante));
    }

    // GET /api/v1/sesiones/cliente/{idCliente}
    // Lista las sesiones de un cliente específico
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<SesionResponse>> obtenerPorCliente(
            @PathVariable Long idCliente
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorCliente(idCliente));
    }

    // GET /api/v1/sesiones/estudio/{idEstudio}
    // Lista las sesiones de un estudio específico
    @GetMapping("/estudio/{idEstudio}")
    public ResponseEntity<List<SesionResponse>> obtenerPorEstudio(
            @PathVariable Long idEstudio
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorEstudio(idEstudio));
    }

    // GET /api/v1/sesiones/pago/{idPago}
    // Lista las sesiones de un pago específico
    @GetMapping("/pago/{idPago}")
    public ResponseEntity<List<SesionResponse>> obtenerPorPago(
            @PathVariable Long idPago
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorPago(idPago));
    }

    // GET /api/v1/sesiones/tatuador/{idTatuador}
    // Lista las sesiones de un tatuador específico
    @GetMapping("/tatuador/{idTatuador}")
    public ResponseEntity<List<SesionResponse>> obtenerPorTatuador(
            @PathVariable Long idTatuador
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorTatuador(idTatuador));
    }

    // GET /api/v1/sesiones/tatuaje/{idTatuaje}
    // Lista las sesiones de un tatuaje específico
    @GetMapping("/tatuaje/{idTatuaje}")
    public ResponseEntity<List<SesionResponse>> obtenerPorTatuaje(
            @PathVariable Long idTatuaje
    ) {
        return ResponseEntity.ok(sesionService.obtenerPorTatuaje(idTatuaje));
    }

    // POST /api/v1/sesiones
    // Crea una nueva sesion validando el request
    @PostMapping
    public ResponseEntity<SesionResponse> guardar(
            @Valid @RequestBody SesionRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sesionService.guardar(request));
    }

    // PUT /api/v1/sesiones/{id}
    // Actualiza una sesion existente
    @PutMapping("/{id}")
    public ResponseEntity<SesionResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SesionRequest request
    ) {
        return ResponseEntity.ok(sesionService.actualizar(id, request));
    }

    // DELETE /api/v1/sesiones/{id}
    // Elimina una sesion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        sesionService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}