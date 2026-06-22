package com.vendedor_vm.vendedor.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.vendedor_vm.vendedor.models.VendedorModel;
import com.vendedor_vm.vendedor.repositories.VendedorRepository;
import com.vendedor_vm.vendedor.services.VendedorService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class VendedorServiceTest {

    @Autowired
    private VendedorService vendedorService;

    @MockBean
    private VendedorRepository vendedorRepository;

    @Test
    public void testObtenerTodos() {

        VendedorModel vendedor = new VendedorModel();

        vendedor.setId(1L);
        vendedor.setNombre("makuza tattoo supply");
        vendedor.setUbicacion("millers 289, providencia, chile");

        when(vendedorRepository.findAll())
                .thenReturn(List.of(vendedor));

        List<VendedorModel> vendedores =
                vendedorService.obtenerTodos();

        assertNotNull(vendedores);
        assertEquals(1, vendedores.size());
    }

    @Test
    public void testObtenerPorId() {

        Long id = 1L;

        VendedorModel vendedor = new VendedorModel();

        vendedor.setId(id);
        vendedor.setNombre("makuza tattoo supply");
        vendedor.setUbicacion("millers 289, providencia, chile");

        when(vendedorRepository.findById(id))
                .thenReturn(Optional.of(vendedor));

        Optional<VendedorModel> encontrado =
                vendedorService.obtenerPorId(id);

        assertTrue(encontrado.isPresent());
        assertEquals(id, encontrado.get().getId());
    }

    @Test
    public void testObtenerPorNombre() {

        String nombre = "makuza tattoo supply";

        VendedorModel vendedor = new VendedorModel();

        vendedor.setId(1L);
        vendedor.setNombre(nombre);
        vendedor.setUbicacion("millers 289, providencia, chile");

        when(vendedorRepository.findByNombre(nombre))
                .thenReturn(Optional.of(vendedor));

        Optional<VendedorModel> encontrado =
                vendedorService.obtenerPorNombre(nombre);

        assertTrue(encontrado.isPresent());
        assertEquals(nombre, encontrado.get().getNombre());
    }

    @Test
    public void testGuardar() {

        VendedorModel vendedor = new VendedorModel();

        vendedor.setId(1L);
        vendedor.setNombre("makuza tattoo supply");
        vendedor.setUbicacion("millers 289, providencia, chile");

        when(vendedorRepository.save(vendedor))
                .thenReturn(vendedor);

        VendedorModel guardado =
                vendedorService.guardar(vendedor);

        assertNotNull(guardado);
        assertEquals("makuza tattoo supply", guardado.getNombre());
    }

    @Test
    public void testEliminar() {

        Long id = 1L;

        doNothing().when(vendedorRepository)
                .deleteById(id);

        vendedorService.eliminar(id);

        verify(vendedorRepository, times(1))
                .deleteById(id);
    }
}