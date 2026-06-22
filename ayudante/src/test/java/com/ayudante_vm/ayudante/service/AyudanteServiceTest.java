package com.ayudante_vm.ayudante.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ayudante_vm.ayudante.models.AyudanteModel;
import com.ayudante_vm.ayudante.repositories.AyudanteRepository;
import com.ayudante_vm.ayudante.services.AyudanteService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class AyudanteServiceTest {

    @Autowired
    private AyudanteService ayudanteService;

    @MockBean
    private AyudanteRepository ayudanteRepository;

    @Test
    public void testObtenerTodos() {

        AyudanteModel ayudante = new AyudanteModel();
        ayudante.setId(1L);
        ayudante.setRut("34.111.111-1");
        ayudante.setNombre("lara");
        ayudante.setApellido("ibañez");
        ayudante.setTelefono("56989264826");

        when(ayudanteRepository.findAll())
                .thenReturn(List.of(ayudante));

        List<AyudanteModel> ayudantes =
                ayudanteService.obtenerTodos();

        assertNotNull(ayudantes);
        assertEquals(1, ayudantes.size());
    }

    @Test
    public void testObtenerPorId() {

        Long id = 1L;

        AyudanteModel ayudante = new AyudanteModel();
        ayudante.setId(id);
        ayudante.setRut("34.111.111-1");
        ayudante.setNombre("lara");
        ayudante.setApellido("ibañez");
        ayudante.setTelefono("56989264826");

        when(ayudanteRepository.findById(id))
                .thenReturn(Optional.of(ayudante));

        Optional<AyudanteModel> encontrado =
                ayudanteService.obtenerPorId(id);

        assertTrue(encontrado.isPresent());
        assertEquals(id, encontrado.get().getId());
    }

    @Test
    public void testObtenerPorRut() {

        String rut = "34.111.111-1";

        AyudanteModel ayudante = new AyudanteModel();
        ayudante.setId(1L);
        ayudante.setRut(rut);
        ayudante.setNombre("lara");
        ayudante.setApellido("ibañez");
        ayudante.setTelefono("56989264826");

        when(ayudanteRepository.findByRut(rut))
                .thenReturn(Optional.of(ayudante));

        Optional<AyudanteModel> encontrado =
                ayudanteService.obtenerPorRut(rut);

        assertTrue(encontrado.isPresent());
        assertEquals(rut, encontrado.get().getRut());
    }

    @Test
    public void testGuardar() {

        AyudanteModel ayudante = new AyudanteModel();
        ayudante.setId(1L);
        ayudante.setRut("34.111.111-1");
        ayudante.setNombre("lara");
        ayudante.setApellido("ibañez");
        ayudante.setTelefono("56989264826");

        when(ayudanteRepository.save(ayudante))
                .thenReturn(ayudante);

        AyudanteModel guardado =
                ayudanteService.guardar(ayudante);

        assertNotNull(guardado);
        assertEquals("lara", guardado.getNombre());
    }

    @Test
    public void testEliminar() {

        Long id = 1L;

        doNothing().when(ayudanteRepository)
                .deleteById(id);

        ayudanteService.eliminar(id);

        verify(ayudanteRepository, times(1))
                .deleteById(id);
    }
}