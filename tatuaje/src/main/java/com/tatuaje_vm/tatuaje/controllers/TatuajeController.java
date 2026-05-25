package com.tatuaje_vm.tatuaje.controllers;
import com.tatuaje_vm.tatuaje.models.TatuajeModel;
import com.tatuaje_vm.tatuaje.services.TatuajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tatuajes")
public class TatuajeController {
    private final TatuajeService tatuajeService;

    public TatuajeController(TatuajeService tatuajeService) {
        this.tatuajeService = tatuajeService;
    }

    @GetMapping
    public List<TatuajeModel> listar() {
        return tatuajeService.obtenerTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TatuajeModel> obtenerPorId(@PathVariable Long id) {
        Optional<TatuajeModel> tatuaje = tatuajeService.obtenerPorId(id);
        return tatuaje.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/descripcion/{descripcion}")
    public ResponseEntity<TatuajeModel> obtenerPorDescripcion(@PathVariable String descripcion) {
        Optional<TatuajeModel> tatuaje = tatuajeService.obtenerPorDescripcion(descripcion);
        return tatuaje.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TatuajeModel> guardar(@RequestBody TatuajeModel tatuaje) {
        return ResponseEntity.ok(tatuajeService.guardar(tatuaje));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tatuajeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
