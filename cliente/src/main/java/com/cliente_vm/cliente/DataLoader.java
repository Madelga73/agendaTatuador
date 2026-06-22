package com.cliente_vm.cliente;

import com.cliente_vm.cliente.models.ClienteModel;
import com.cliente_vm.cliente.repositories.ClienteRepository;

import net.datafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {

            ClienteModel cliente = new ClienteModel();

            cliente.setRut(
                    faker.number().numberBetween(10000000, 25000000)
                            + "-" +
                            faker.number().numberBetween(0, 10)
            );

            cliente.setNombre(
                    faker.name().firstName()
            );

            cliente.setApellido(
                    faker.name().lastName()
            );

            cliente.setTelefono(
                    "569" + faker.number().digits(8)
            );

            clienteRepository.save(cliente);
        }
    }
}