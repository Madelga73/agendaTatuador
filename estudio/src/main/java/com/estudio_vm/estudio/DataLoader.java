package com.estudio_vm.estudio;

import com.estudio_vm.estudio.models.EstudioModel;
import com.estudio_vm.estudio.repositories.EstudioRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EstudioRepository estudioRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {

            EstudioModel estudio = new EstudioModel();

            estudio.setNombre(faker.company().name());
            estudio.setUbicacion(
                    faker.address().streetAddress() + ", " +
                    faker.address().city()
            );

            estudioRepository.save(estudio);
        }
    }
}