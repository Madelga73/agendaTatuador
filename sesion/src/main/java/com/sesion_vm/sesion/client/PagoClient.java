package com.sesion_vm.sesion.client;
import com.sesion_vm.sesion.config.FeignConfig;
import com.sesion_vm.sesion.dtos.response.PagoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "pago-client",
        url = "${pago.service.url}",
        configuration = FeignConfig.class
)
public interface PagoClient {
    @GetMapping("/api/v1/pagos/{id}")
    PagoResponse obtenerPagoPorId(@PathVariable("id") Long id);
}
