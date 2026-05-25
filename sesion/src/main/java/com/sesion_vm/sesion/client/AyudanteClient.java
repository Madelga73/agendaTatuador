package com.sesion_vm.sesion.client;
import com.sesion_vm.sesion.config.FeignConfig;
import com.sesion_vm.sesion.dtos.response.AyudanteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ayudante-client",
        url = "${ayudante.service.url}",
        configuration = FeignConfig.class
)
public interface AyudanteClient {
    @GetMapping("/api/v1/ayudantes/{id}")
    AyudanteResponse obtenerAyudantePorId(@PathVariable("id") Long id);
}
