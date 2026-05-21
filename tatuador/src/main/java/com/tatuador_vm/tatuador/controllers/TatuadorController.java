package com.tatuador_vm.tatuador.controllers;
import com.tatuador_vm.tatuador.models.TatuadorModel;
import com.tatuador_vm.tatuador.services.TatuadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tatuadores")
public class TatuadorController{
    private final TatuadorService tatuadorService;

    public TatuadorController(TatuadorService tatuadorService) {
        this.tatuadorService = tatuadorService;
    }

    @GetMapping
    public List<TatuadorModel> listar() {
        return tatuadorService.obtenerTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TatuadorModel> obtenerPorId(@PathVariable Long id) {
        Optional<TatuadorModel> cliente = tatuadorService.obtenerPorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<TatuadorModel> obtenerPorRut(@PathVariable String rut) {
        Optional<TatuadorModel> cliente = tatuadorService.obtenerPorRut(rut);
        return cliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TatuadorModel> guardar(@RequestBody TatuadorModel tatuador) {
        return ResponseEntity.ok(tatuadorService.guardar(tatuador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tatuadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
