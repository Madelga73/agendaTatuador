package com.pago_vm.pago;

import com.pago_vm.pago.models.PagoModel;
import com.pago_vm.pago.repositories.PagoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        String[] metodosPago = {
                "Efectivo",
                "Debito",
                "Credito",
                "Transferencia"
        };

        for (int i = 0; i < 20; i++) {

            PagoModel pago = new PagoModel();

            pago.setNumBoleta(
                    "BOL-" + faker.number().digits(6)
            );

            pago.setMontoTotal(
                    faker.number().numberBetween(50000, 500000)
            );

            pago.setMontoInsumos(
                    faker.number().numberBetween(5000, 50000)
            );

            pago.setMetodoPago(
                    metodosPago[faker.number().numberBetween(0, metodosPago.length)]
            );

            pagoRepository.save(pago);
        }
    }
}