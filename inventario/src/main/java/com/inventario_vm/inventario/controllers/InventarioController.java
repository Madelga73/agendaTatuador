package com.inventario_vm.inventario.controllers;

import com.inventario_vm.inventario.services.InventarioService;

import com.inventario_vm.inventario.dtos.request.InventarioRequest;
import com.inventario_vm.inventario.dtos.response.InventarioResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

// Expone los endpoints REST del microservicio de inventario
@RestController
@RequestMapping("/api/v1/inventarios")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // GET /api/v1/inventarios
    // Lista todos los inventarios
    @GetMapping
    public ResponseEntity<List<InventarioResponse>> listar() {
        return ResponseEntity.ok(inventarioService.obtenerTodos());
    }

    // GET /api/v1/inventarios/{id}
    // Busca un inventario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.obtenerPorId(id));
    }

    // GET /api/v1/inventarios/tatuador/{idTatuador}
    // Lista los inventarios de un tatuador específico
    @GetMapping("/tatuador/{idTatuador}")
    public ResponseEntity<List<InventarioResponse>> obtenerPorTatuador(
            @PathVariable Long idTatuador
    ) {
        return ResponseEntity.ok(inventarioService.obtenerPorTatuador(idTatuador));
    }

    // GET /api/v1/inventarios/vendedor/{idVendedor}
    // Lista los inventarios de un vendedor específico
    @GetMapping("/vendedor/{idVendedor}")
    public ResponseEntity<List<InventarioResponse>> obtenerPorVendedor(
            @PathVariable Long idVendedor
    ) {
        return ResponseEntity.ok(inventarioService.obtenerPorVendedor(idVendedor));
    }

    // POST /api/v1/inventarios
    // Crea un nuevo inventario validando el request
    @PostMapping
    public ResponseEntity<InventarioResponse> guardar(
            @Valid @RequestBody InventarioRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventarioService.guardar(request));
    }

    // PUT /api/v1/inventarios/{id}
    // Actualiza un inventario existente
    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody InventarioRequest request
    ) {
        return ResponseEntity.ok(inventarioService.actualizar(id, request));
    }

    // DELETE /api/v1/inventarios/{id}
    // Elimina un inventario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        inventarioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}