package com.estudio_vm.estudio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.estudio_vm.estudio.models.EstudioModel;
import com.estudio_vm.estudio.repositories.EstudioRepository;
import com.estudio_vm.estudio.services.EstudioService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class EstudioServiceTest {

    @Autowired
    private EstudioService estudioService;

    @MockBean
    private EstudioRepository estudioRepository;

    @Test
    public void testObtenerTodos() {

        EstudioModel estudio = new EstudioModel();

        estudio.setId(1L);
        estudio.setNombre("katarsis tattoo estudio");
        estudio.setUbicacion("san emilio 169, providencia, chile");

        when(estudioRepository.findAll())
                .thenReturn(List.of(estudio));

        List<EstudioModel> estudios =
                estudioService.obtenerTodos();

        assertNotNull(estudios);
        assertEquals(1, estudios.size());
    }

    @Test
    public void testObtenerPorId() {

        Long id = 1L;

        EstudioModel estudio = new EstudioModel();

        estudio.setId(id);
        estudio.setNombre("katarsis tattoo estudio");
        estudio.setUbicacion("san emilio 169, providencia, chile");

        when(estudioRepository.findById(id))
                .thenReturn(Optional.of(estudio));

        Optional<EstudioModel> encontrado =
                estudioService.obtenerPorId(id);

        assertTrue(encontrado.isPresent());
        assertEquals(id, encontrado.get().getId());
    }

    @Test
    public void testObtenerPorNombre() {

        String nombre = "katarsis tattoo estudio";

        EstudioModel estudio = new EstudioModel();

        estudio.setId(1L);
        estudio.setNombre(nombre);
        estudio.setUbicacion("san emilio 169, providencia, chile");

        when(estudioRepository.findByNombre(nombre))
                .thenReturn(Optional.of(estudio));

        Optional<EstudioModel> encontrado =
                estudioService.obtenerPorNombre(nombre);

        assertTrue(encontrado.isPresent());
        assertEquals(nombre, encontrado.get().getNombre());
    }

    @Test
    public void testGuardar() {

        EstudioModel estudio = new EstudioModel();

        estudio.setId(1L);
        estudio.setNombre("katarsis tattoo estudio");
        estudio.setUbicacion("san emilio 169, providencia, chile");

        when(estudioRepository.save(estudio))
                .thenReturn(estudio);

        EstudioModel guardado =
                estudioService.guardar(estudio);

        assertNotNull(guardado);
        assertEquals("katarsis tattoo estudio", guardado.getNombre());
    }

    @Test
    public void testEliminar() {

        Long id = 1L;

        doNothing().when(estudioRepository)
                .deleteById(id);

        estudioService.eliminar(id);

        verify(estudioRepository, times(1))
                .deleteById(id);
    }
}