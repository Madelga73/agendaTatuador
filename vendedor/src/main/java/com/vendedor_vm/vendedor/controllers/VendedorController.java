package com.vendedor_vm.vendedor.controllers;
import com.vendedor_vm.vendedor.models.VendedorModel;
import com.vendedor_vm.vendedor.services.VendedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vendedores")
public class VendedorController {
    private final VendedorService vendedorService;
    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public List<VendedorModel> listar() {
        return vendedorService.obtenerTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<VendedorModel> obtenerPorId(@PathVariable Long id) {
        Optional<VendedorModel> vendedor = vendedorService.obtenerPorId(id);
        return vendedor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<VendedorModel> obtenerPorNombre(@PathVariable String nombre) {
        Optional<VendedorModel> vendedor = vendedorService.obtenerPorNombre(nombre);
        return vendedor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VendedorModel> guardar(@RequestBody VendedorModel vendedor) {
        return ResponseEntity.ok(vendedorService.guardar(vendedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vendedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
