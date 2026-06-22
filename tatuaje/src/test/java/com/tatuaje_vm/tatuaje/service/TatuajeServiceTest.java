package com.tatuaje_vm.tatuaje.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tatuaje_vm.tatuaje.models.TatuajeModel;
import com.tatuaje_vm.tatuaje.repositories.TatuajeRepository;
import com.tatuaje_vm.tatuaje.services.TatuajeService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class TatuajeServiceTest {

    @Autowired
    private TatuajeService tatuajeService;

    @MockBean
    private TatuajeRepository tatuajeRepository;

    @Test
    public void testObtenerTodos() {

        TatuajeModel tatuaje = new TatuajeModel();

        tatuaje.setId(1L);
        tatuaje.setDescripcion("Dragon japones en brazo completo con detalles en negro y rojo");
        tatuaje.setZona("Brazo izquierdo");
        tatuaje.setEstilo("Japones");

        when(tatuajeRepository.findAll())
                .thenReturn(List.of(tatuaje));

        List<TatuajeModel> tatuajes =
                tatuajeService.obtenerTodos();

        assertNotNull(tatuajes);
        assertEquals(1, tatuajes.size());
    }

    @Test
    public void testObtenerPorId() {

        Long id = 1L;

        TatuajeModel tatuaje = new TatuajeModel();

        tatuaje.setId(id);
        tatuaje.setDescripcion("Dragon japones en brazo completo con detalles en negro y rojo");
        tatuaje.setZona("Brazo izquierdo");
        tatuaje.setEstilo("Japones");

        when(tatuajeRepository.findById(id))
                .thenReturn(Optional.of(tatuaje));

        Optional<TatuajeModel> encontrado =
                tatuajeService.obtenerPorId(id);

        assertTrue(encontrado.isPresent());
        assertEquals(id, encontrado.get().getId());
    }

    @Test
    public void testObtenerPorDescripcion() {

        String descripcion = "Dragon japones en brazo completo con detalles en negro y rojo";

        TatuajeModel tatuaje = new TatuajeModel();

        tatuaje.setId(1L);
        tatuaje.setDescripcion(descripcion);
        tatuaje.setZona("Brazo izquierdo");
        tatuaje.setEstilo("Japones");

        when(tatuajeRepository.findByDescripcion(descripcion))
                .thenReturn(Optional.of(tatuaje));

        Optional<TatuajeModel> encontrado =
                tatuajeService.obtenerPorDescripcion(descripcion);

        assertTrue(encontrado.isPresent());
        assertEquals(descripcion, encontrado.get().getDescripcion());
    }

    @Test
    public void testGuardar() {

        TatuajeModel tatuaje = new TatuajeModel();

        tatuaje.setId(1L);
        tatuaje.setDescripcion("Dragon japones en brazo completo con detalles en negro y rojo");
        tatuaje.setZona("Brazo izquierdo");
        tatuaje.setEstilo("Japones");

        when(tatuajeRepository.save(tatuaje))
                .thenReturn(tatuaje);

        TatuajeModel guardado =
                tatuajeService.guardar(tatuaje);

        assertNotNull(guardado);
        assertEquals("Japones", guardado.getEstilo());
    }

    @Test
    public void testEliminar() {

        Long id = 1L;

        doNothing().when(tatuajeRepository)
                .deleteById(id);

        tatuajeService.eliminar(id);

        verify(tatuajeRepository, times(1))
                .deleteById(id);
    }
}