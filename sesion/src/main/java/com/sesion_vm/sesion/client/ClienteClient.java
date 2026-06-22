package com.sesion_vm.sesion.client;
import com.sesion_vm.sesion.config.FeignConfig;
import com.sesion_vm.sesion.dtos.response.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "cliente-client",
        url = "${cliente.service.url}",
        configuration = FeignConfig.class
)
public interface ClienteClient {
    @GetMapping("/api/v1/clientes/{id}")
    ClienteResponse obtenerClientePorId(@PathVariable("id") Long id);
}
