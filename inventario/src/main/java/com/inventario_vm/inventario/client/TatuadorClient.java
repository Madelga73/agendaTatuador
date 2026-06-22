package com.inventario_vm.inventario.client;
import com.inventario_vm.inventario.config.FeignConfig;
import com.inventario_vm.inventario.dtos.response.TatuadorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "tatuador-client",
        url = "${tatuador.service.url}",
        configuration = FeignConfig.class
)
public interface TatuadorClient {
    @GetMapping("/api/v1/tatuadores/{id}")
    TatuadorResponse obtenerTatuadorPorId(@PathVariable("id") Long id);
}
