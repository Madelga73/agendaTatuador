package com.estudio_vm.estudio.controllers;
import com.estudio_vm.estudio.models.EstudioModel;
import com.estudio_vm.estudio.services.EstudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/estudios")
public class EstudioController {
    private final EstudioService estudioService;
    public EstudioController(EstudioService estudioService) {
        this.estudioService = estudioService;
    }

    @GetMapping
    public List<EstudioModel> listar() {
        return estudioService.obtenerTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstudioModel> obtenerPorId(@PathVariable Long id) {
        Optional<EstudioModel> estudio = estudioService.obtenerPorId(id);
        return estudio.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EstudioModel> obtenerPorNombre(@PathVariable String nombre) {
        Optional<EstudioModel> estudio = estudioService.obtenerPorNombre(nombre);
        return estudio.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstudioModel> guardar(@RequestBody EstudioModel estudio) {
        return ResponseEntity.ok(estudioService.guardar(estudio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estudioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
