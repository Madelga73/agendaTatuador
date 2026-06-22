package com.tatuador_vm.tatuador;

import com.tatuador_vm.tatuador.models.TatuadorModel;
import com.tatuador_vm.tatuador.repositories.TatuadorRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TatuadorRepository tatuadorRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        String[] especialidades = {
                "Blackwork",
                "Realismo",
                "Anime",
                "Tradicional",
                "Geometrico",
                "Fine Line",
                "Lettering",
                "Japones"
        };

        for (int i = 0; i < 20; i++) {

            TatuadorModel tatuador = new TatuadorModel();

            tatuador.setRut(
                    faker.number().digits(8) + "-" +
                    faker.number().digit()
            );

            tatuador.setNombre(
                    faker.name().firstName()
            );

            tatuador.setApellido(
                    faker.name().lastName()
            );

            tatuador.setEspecialidad(
                    especialidades[
                            faker.number().numberBetween(0, especialidades.length)
                    ]
            );

            tatuador.setTelefono(
                    "569" + faker.number().digits(8)
            );

            tatuadorRepository.save(tatuador);
        }
    }
}