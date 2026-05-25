package com.inventario_vm.inventario.client;
import com.inventario_vm.inventario.config.FeignConfig;
import com.inventario_vm.inventario.dtos.response.VendedorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "Vendedor-client",
        url = "${Vendedor.service.url}",
        configuration = FeignConfig.class
)
public interface VendedorClient {
    @GetMapping("/api/v1/vendedores/{id}")
    VendedorResponse obtenerVendedorPorId(@PathVariable("id") Long id);
}
