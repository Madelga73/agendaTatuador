package com.sesion_vm.sesion.client;
import com.sesion_vm.sesion.config.FeignConfig;
import com.sesion_vm.sesion.dtos.response.TatuajeResponse;
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
