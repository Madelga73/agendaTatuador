package com.vendedor_vm.vendedor;

import com.vendedor_vm.vendedor.models.VendedorModel;
import com.vendedor_vm.vendedor.repositories.VendedorRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        // =============================================================================
        // PASO: Generar vendedores (20 registros)
        // =============================================================================
        // faker.company().name()      -> Nombre de empresa aleatorio.
        // faker.address().fullAddress() -> Dirección aleatoria.
        // =============================================================================
        for (int i = 0; i < 20; i++) {

            VendedorModel vendedor = new VendedorModel();

            vendedor.setNombre(faker.company().name());
            vendedor.setUbicacion(faker.address().fullAddress());

            vendedorRepository.save(vendedor);
        }
    }
}