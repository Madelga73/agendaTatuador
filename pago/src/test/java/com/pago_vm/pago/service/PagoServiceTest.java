package com.pago_vm.pago.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.pago_vm.pago.models.PagoModel;
import com.pago_vm.pago.repositories.PagoRepository;
import com.pago_vm.pago.services.PagoService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class PagoServiceTest {

    @Autowired
    private PagoService pagoService;

    @MockBean
    private PagoRepository pagoRepository;

    @Test
    public void testObtenerTodos() {

        PagoModel pago = new PagoModel();

        pago.setId(1L);
        pago.setNumBoleta("1001");
        pago.setMontoTotal(45000);
        pago.setMontoInsumos(12000);
        pago.setMetodoPago("efectivo");

        when(pagoRepository.findAll())
                .thenReturn(List.of(pago));

        List<PagoModel> pagos = pagoService.obtenerTodos();

        assertNotNull(pagos);
        assertEquals(1, pagos.size());
    }

    @Test
    public void testObtenerPorId() {

        Long id = 1L;

        PagoModel pago = new PagoModel();

        pago.setId(id);
        pago.setNumBoleta("1001");
        pago.setMontoTotal(45000);
        pago.setMontoInsumos(12000);
        pago.setMetodoPago("efectivo");

        when(pagoRepository.findById(id))
                .thenReturn(Optional.of(pago));

        Optional<PagoModel> encontrado =
                pagoService.obtenerPorId(id);

        assertTrue(encontrado.isPresent());
        assertEquals(id, encontrado.get().getId());
    }

    @Test
    public void testObtenerPorNumBoleta() {

        String numBoleta = "1001";

        PagoModel pago = new PagoModel();

        pago.setId(1L);
        pago.setNumBoleta(numBoleta);
        pago.setMontoTotal(45000);
        pago.setMontoInsumos(12000);
        pago.setMetodoPago("efectivo");

        when(pagoRepository.findByNumBoleta(numBoleta))
                .thenReturn(Optional.of(pago));

        Optional<PagoModel> encontrado =
                pagoService.obtenerPorNumBoleta(numBoleta);

        assertTrue(encontrado.isPresent());
        assertEquals(numBoleta, encontrado.get().getNumBoleta());
    }

    @Test
    public void testGuardar() {

        PagoModel pago = new PagoModel();

        pago.setId(1L);
        pago.setNumBoleta("1001");
        pago.setMontoTotal(45000);
        pago.setMontoInsumos(12000);
        pago.setMetodoPago("efectivo");

        when(pagoRepository.save(pago))
                .thenReturn(pago);

        PagoModel guardado =
                pagoService.guardar(pago);

        assertNotNull(guardado);
        assertEquals("1001", guardado.getNumBoleta());
    }

    @Test
    public void testEliminar() {

        Long id = 1L;

        doNothing().when(pagoRepository)
                .deleteById(id);

        pagoService.eliminar(id);

        verify(pagoRepository, times(1))
                .deleteById(id);
    }
}