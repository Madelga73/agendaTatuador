package com.resultado_vm.resultado.controllers;

import com.resultado_vm.resultado.services.ResultadoService;

import com.resultado_vm.resultado.dtos.request.ResultadoRequest;
import com.resultado_vm.resultado.dtos.response.ResultadoResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

// Expone los endpoints REST del microservicio de resultado
@RestController
@RequestMapping("/api/v1/resultados")
public class ResultadoController {

    private final ResultadoService resultadoService;

    public ResultadoController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    // GET /api/v1/resultados
    // Lista todos los resultados
    @GetMapping
    public ResponseEntity<List<ResultadoResponse>> listar() {
        return ResponseEntity.ok(resultadoService.obtenerTodos());
    }

    // GET /api/v1/resultados/{id}
    // Busca un resultado por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ResultadoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(resultadoService.obtenerPorId(id));
    }

    // GET /api/v1/resultados/cliente/{idCliente}
    // Lista los resultados de un cliente específico
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ResultadoResponse>> obtenerPorCliente(
            @PathVariable Long idCliente
    ) {
        return ResponseEntity.ok(resultadoService.obtenerPorCliente(idCliente));
    }

    // GET /api/v1/resultados/tatuaje/{idTatuaje}
    // Lista los resultados de un tatuaje específico
    @GetMapping("/tatuaje/{idTatuaje}")
    public ResponseEntity<List<ResultadoResponse>> obtenerPorTatuaje(
            @PathVariable Long idTatuaje
    ) {
        return ResponseEntity.ok(resultadoService.obtenerPorTatuaje(idTatuaje));
    }

    // POST /api/v1/resultados
    // Crea un nuevo resultado validando el request
    @PostMapping
    public ResponseEntity<ResultadoResponse> guardar(
            @Valid @RequestBody ResultadoRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultadoService.guardar(request));
    }

    // PUT /api/v1/resultados/{id}
    // Actualiza un resultado existente
    @PutMapping("/{id}")
    public ResponseEntity<ResultadoResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ResultadoRequest request
    ) {
        return ResponseEntity.ok(resultadoService.actualizar(id, request));
    }

    // DELETE /api/v1/resultados/{id}
    // Elimina un resultado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        resultadoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}