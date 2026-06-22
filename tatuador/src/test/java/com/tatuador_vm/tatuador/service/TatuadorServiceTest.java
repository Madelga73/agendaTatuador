package com.tatuador_vm.tatuador.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tatuador_vm.tatuador.models.TatuadorModel;
import com.tatuador_vm.tatuador.repositories.TatuadorRepository;
import com.tatuador_vm.tatuador.services.TatuadorService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class TatuadorServiceTest {

    @Autowired
    private TatuadorService tatuadorService;

    @MockBean
    private TatuadorRepository tatuadorRepository;

    @Test
    public void testObtenerTodos() {

        TatuadorModel tatuador = new TatuadorModel();

        tatuador.setId(1L);
        tatuador.setRut("14.256.789-3");
        tatuador.setNombre("Matias");
        tatuador.setApellido("Suarez");
        tatuador.setEspecialidad("Blackwork");
        tatuador.setTelefono("569325435441");

        when(tatuadorRepository.findAll())
                .thenReturn(List.of(tatuador));

        List<TatuadorModel> tatuadores =
                tatuadorService.obtenerTodos();

        assertNotNull(tatuadores);
        assertEquals(1, tatuadores.size());
    }

    @Test
    public void testObtenerPorId() {

        Long id = 1L;

        TatuadorModel tatuador = new TatuadorModel();

        tatuador.setId(id);
        tatuador.setRut("14.256.789-3");
        tatuador.setNombre("Matias");
        tatuador.setApellido("Suarez");
        tatuador.setEspecialidad("Blackwork");
        tatuador.setTelefono("569325435441");

        when(tatuadorRepository.findById(id))
                .thenReturn(Optional.of(tatuador));

        Optional<TatuadorModel> encontrado =
                tatuadorService.obtenerPorId(id);

        assertTrue(encontrado.isPresent());
        assertEquals(id, encontrado.get().getId());
    }

    @Test
    public void testObtenerPorRut() {

        String rut = "14.256.789-3";

        TatuadorModel tatuador = new TatuadorModel();

        tatuador.setId(1L);
        tatuador.setRut(rut);
        tatuador.setNombre("Matias");
        tatuador.setApellido("Suarez");
        tatuador.setEspecialidad("Blackwork");
        tatuador.setTelefono("569325435441");

        when(tatuadorRepository.findByRut(rut))
                .thenReturn(Optional.of(tatuador));

        Optional<TatuadorModel> encontrado =
                tatuadorService.obtenerPorRut(rut);

        assertTrue(encontrado.isPresent());
        assertEquals(rut, encontrado.get().getRut());
    }

    @Test
    public void testGuardar() {

        TatuadorModel tatuador = new TatuadorModel();

        tatuador.setId(1L);
        tatuador.setRut("14.256.789-3");
        tatuador.setNombre("Matias");
        tatuador.setApellido("Suarez");
        tatuador.setEspecialidad("Blackwork");
        tatuador.setTelefono("569325435441");

        when(tatuadorRepository.save(tatuador))
                .thenReturn(tatuador);

        TatuadorModel guardado =
                tatuadorService.guardar(tatuador);

        assertNotNull(guardado);
        assertEquals("Matias", guardado.getNombre());
    }

    @Test
    public void testEliminar() {

        Long id = 1L;

        doNothing().when(tatuadorRepository)
                .deleteById(id);

        tatuadorService.eliminar(id);

        verify(tatuadorRepository, times(1))
                .deleteById(id);
    }
}