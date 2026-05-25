package com.pago_vm.pago.controllers;
import com.pago_vm.pago.models.PagoModel;
import com.pago_vm.pago.services.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {
    private final PagoService pagoService;
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public List<PagoModel> listar() {
        return pagoService.obtenerTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PagoModel> obtenerPorId(@PathVariable Long id) {
        Optional<PagoModel> estudio = pagoService.obtenerPorId(id);
        return estudio.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/num_boleta/{num_boleta}")
    public ResponseEntity<PagoModel> obtenerPorNumBoleta(@PathVariable String numBoleta) {
        Optional<PagoModel> pago = pagoService.obtenerPorNumBoleta(numBoleta);
        return pago.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PagoModel> guardar(@RequestBody PagoModel pago) {
        return ResponseEntity.ok(pagoService.guardar(pago));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
