package com.inventario_vm.inventario;

import com.inventario_vm.inventario.models.InventarioModel;
import com.inventario_vm.inventario.repositories.InventarioRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        String[] insumos = {
                "Tinta Negra",
                "Tinta Roja",
                "Tinta Azul",
                "Agujas RL",
                "Agujas Magnum",
                "Guantes Latex",
                "Papel Transfer",
                "Stencil Spray",
                "Grip Desechable",
                "Vaselina"
        };

        for (int i = 0; i < 20; i++) {

            InventarioModel inventario = new InventarioModel();

            inventario.setIdTatuador(
                    (long) faker.number().numberBetween(1, 4)
            );

            inventario.setIdVendedor(
                    (long) faker.number().numberBetween(1, 4)
            );

            inventario.setInsumo(
                    insumos[faker.number().numberBetween(0, insumos.length)]
            );

            inventario.setCantidad(
                    faker.number().numberBetween(1, 100)
            );

            inventario.setPrecioUnitario(
                    faker.number().numberBetween(1000, 50000)
            );

            inventarioRepository.save(inventario);
        }
    }
}