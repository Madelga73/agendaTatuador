package com.sesion_vm.sesion;

import com.sesion_vm.sesion.models.SesionModel;
import com.sesion_vm.sesion.Repositories.SesionRepository;

import net.datafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private SesionRepository sesionRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {

            SesionModel sesion = new SesionModel();

            sesion.setIdAyudante((long) faker.number().numberBetween(1, 4));
            sesion.setIdCliente((long) faker.number().numberBetween(1, 4));
            sesion.setIdEstudio((long) faker.number().numberBetween(1, 4));
            sesion.setIdPago((long) faker.number().numberBetween(1, 4));
            sesion.setIdTatuador((long) faker.number().numberBetween(1, 4));
            sesion.setIdTatuaje((long) faker.number().numberBetween(1, 4));

            sesion.setFechaSesion(
                    LocalDate.now().plusDays(
                            faker.number().numberBetween(1, 30))
            );

            sesion.setHoraSesion(
                    LocalTime.of(
                            faker.number().numberBetween(8, 20),
                            faker.options().option(0, 15, 30, 45)
                    )
            );

            sesion.setDuracionHoras(
                    faker.number().numberBetween(1, 8)
            );

            sesionRepository.save(sesion);
        }
    }
}