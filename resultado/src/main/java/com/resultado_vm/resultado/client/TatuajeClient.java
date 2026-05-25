package com.resultado_vm.resultado.client;
import com.resultado_vm.resultado.config.FeignConfig;
import com.resultado_vm.resultado.dtos.response.TatuajeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "tatuaje-client",
        url = "${tatuaje.service.url}",
        configuration = FeignConfig.class
)
public interface TatuajeClient {
    @GetMapping("/api/v1/tatuajes/{id}")
    TatuajeResponse obtenerTatuajePorId(@PathVariable("id") Long id);
}
