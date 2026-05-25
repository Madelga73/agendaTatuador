package com.sesion_vm.sesion.client;
import com.sesion_vm.sesion.config.FeignConfig;
import com.sesion_vm.sesion.dtos.response.TatuadorResponse;
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
