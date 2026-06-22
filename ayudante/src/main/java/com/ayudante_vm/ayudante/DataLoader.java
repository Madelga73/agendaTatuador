package com.ayudante_vm.ayudante;

import com.ayudante_vm.ayudante.models.AyudanteModel;
import com.ayudante_vm.ayudante.repositories.AyudanteRepository;

import net.datafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AyudanteRepository ayudanteRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {

            AyudanteModel ayudante = new AyudanteModel();

            ayudante.setRut(
                    faker.number().numberBetween(10000000, 25000000)
                            + "-" +
                            faker.number().numberBetween(0, 9)
            );

            ayudante.setNombre(
                    faker.name().firstName()
            );

            ayudante.setApellido(
                    faker.name().lastName()
            );

            ayudante.setTelefono(
                    "569" +
                    faker.number().digits(8)
            );

            ayudanteRepository.save(ayudante);
        }
    }
}