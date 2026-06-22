package com.resultado_vm.resultado;

import com.resultado_vm.resultado.models.ResultadoModel;
import com.resultado_vm.resultado.repositories.ResultadoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        String[] comentarios = {
                "Excelente trabajo y muy profesional",
                "Muy conforme con el resultado",
                "Buen diseño y atención",
                "Superó mis expectativas",
                "Recomendado totalmente",
                "Gran calidad en los detalles",
                "Muy buena experiencia",
                "Trabajo limpio y profesional",
                "Volvería a tatuarme aquí",
                "Resultado espectacular"
        };

        for (int i = 0; i < 20; i++) {

            ResultadoModel resultado = new ResultadoModel();

            resultado.setIdCliente(
                    (long) faker.number().numberBetween(1, 4)
            );

            resultado.setIdTatuaje(
                    (long) faker.number().numberBetween(1, 4)
            );

            resultado.setValoracion(
                    faker.number().numberBetween(1, 11)
            );

            resultado.setComentario(
                    comentarios[faker.number().numberBetween(0, comentarios.length)]
            );

            resultadoRepository.save(resultado);
        }
    }
}