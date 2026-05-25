package com.sesion_vm.sesion.client;
import com.sesion_vm.sesion.config.FeignConfig;
import com.sesion_vm.sesion.dtos.response.EstudioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "estudio-client",
        url = "${estudio.service.url}",
        configuration = FeignConfig.class
)
public interface EstudioClient {
    @GetMapping("/api/v1/estudios/{id}")
    EstudioResponse obtenerEstudioPorId(@PathVariable("id") Long id);
}
