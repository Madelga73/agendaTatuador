package com.ayudante_vm.ayudante.controllers;
import com.ayudante_vm.ayudante.models.AyudanteModel;
import com.ayudante_vm.ayudante.services.AyudanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ayudantes")
public class AyudanteController {
    private final AyudanteService ayudanteService;
    public AyudanteController(AyudanteService ayudanteService) {
        this.ayudanteService = ayudanteService;
    }

    @GetMapping
    public List<AyudanteModel> listar() {
        return ayudanteService.obtenerTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<AyudanteModel> obtenerPorId(@PathVariable Long id) {
        Optional<AyudanteModel> ayudante = ayudanteService.obtenerPorId(id);
        return ayudante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/rut/{rut}")
    public ResponseEntity<AyudanteModel> obtenerPorRut(@PathVariable String rut) {
        Optional<AyudanteModel> ayudante = ayudanteService.obtenerPorRut(rut);
        return ayudante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AyudanteModel> guardar(@RequestBody AyudanteModel ayudante) {
        return ResponseEntity.ok(ayudanteService.guardar(ayudante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ayudanteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
