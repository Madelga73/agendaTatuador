package com.tatuaje_vm.tatuaje;

import com.tatuaje_vm.tatuaje.models.TatuajeModel;
import com.tatuaje_vm.tatuaje.repositories.TatuajeRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TatuajeRepository tatuajeRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        String[] zonas = {
                "Brazo izquierdo",
                "Brazo derecho",
                "Pierna izquierda",
                "Pierna derecha",
                "Espalda",
                "Pecho",
                "Cuello"
        };

        String[] estilos = {
                "Realismo",
                "Anime",
                "Japones",
                "Blackwork",
                "Tradicional",
                "Minimalista"
        };

        for (int i = 0; i < 20; i++) {

            TatuajeModel tatuaje = new TatuajeModel();

            tatuaje.setDescripcion(faker.lorem().sentence(8));

            tatuaje.setZona(
                    zonas[faker.number()
                            .numberBetween(0, zonas.length)]
            );

            tatuaje.setEstilo(
                    estilos[faker.number()
                            .numberBetween(0, estilos.length)]
            );

            tatuajeRepository.save(tatuaje);
        }
    }
}