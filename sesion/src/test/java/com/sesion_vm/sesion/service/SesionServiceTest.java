package com.sesion_vm.sesion.service;

import com.sesion_vm.sesion.Services.SesionService;
import com.sesion_vm.sesion.Repositories.SesionRepository;

import com.sesion_vm.sesion.client.AyudanteClient;
import com.sesion_vm.sesion.client.ClienteClient;
import com.sesion_vm.sesion.client.EstudioClient;
import com.sesion_vm.sesion.client.PagoClient;
import com.sesion_vm.sesion.client.TatuadorClient;
import com.sesion_vm.sesion.client.TatuajeClient;

import com.sesion_vm.sesion.dtos.request.SesionRequest;
import com.sesion_vm.sesion.dtos.response.AyudanteResponse;
import com.sesion_vm.sesion.dtos.response.ClienteResponse;
import com.sesion_vm.sesion.dtos.response.EstudioResponse;
import com.sesion_vm.sesion.dtos.response.PagoResponse;
import com.sesion_vm.sesion.dtos.response.TatuadorResponse;
import com.sesion_vm.sesion.dtos.response.TatuajeResponse;

import com.sesion_vm.sesion.models.SesionModel;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class SesionServiceTest {

    @Autowired
    private SesionService sesionService;

    @MockBean
    private SesionRepository sesionRepository;

    @MockBean
    private AyudanteClient ayudanteClient;

    @MockBean
    private ClienteClient clienteClient;

    @MockBean
    private EstudioClient estudioClient;

    @MockBean
    private PagoClient pagoClient;

    @MockBean
    private TatuadorClient tatuadorClient;

    @MockBean
    private TatuajeClient tatuajeClient;

    @Test
    void testObtenerTodas() {

        SesionModel sesion = new SesionModel();
        sesion.setId(1L);
        sesion.setIdAyudante(1L);
        sesion.setIdCliente(1L);
        sesion.setIdEstudio(1L);
        sesion.setIdPago(1L);
        sesion.setIdTatuador(1L);
        sesion.setIdTatuaje(1L);

        when(sesionRepository.findAll())
                .thenReturn(List.of(sesion));

        when(ayudanteClient.obtenerAyudantePorId(1L))
                .thenReturn(new AyudanteResponse());

        when(clienteClient.obtenerClientePorId(1L))
                .thenReturn(new ClienteResponse());

        when(estudioClient.obtenerEstudioPorId(1L))
                .thenReturn(new EstudioResponse());

        when(pagoClient.obtenerPagoPorId(1L))
                .thenReturn(new PagoResponse());

        when(tatuadorClient.obtenerTatuadorPorId(1L))
                .thenReturn(new TatuadorResponse());

        when(tatuajeClient.obtenerTatuajePorId(1L))
                .thenReturn(new TatuajeResponse());

        assertEquals(1, sesionService.obtenerTodas().size());
    }

    @Test
    void testObtenerPorId() {

        SesionModel sesion = new SesionModel();
        sesion.setId(1L);
        sesion.setIdAyudante(1L);
        sesion.setIdCliente(1L);
        sesion.setIdEstudio(1L);
        sesion.setIdPago(1L);
        sesion.setIdTatuador(1L);
        sesion.setIdTatuaje(1L);

        when(sesionRepository.findById(1L))
                .thenReturn(Optional.of(sesion));

        when(ayudanteClient.obtenerAyudantePorId(1L))
                .thenReturn(new AyudanteResponse());

        when(clienteClient.obtenerClientePorId(1L))
                .thenReturn(new ClienteResponse());

        when(estudioClient.obtenerEstudioPorId(1L))
                .thenReturn(new EstudioResponse());

        when(pagoClient.obtenerPagoPorId(1L))
                .thenReturn(new PagoResponse());

        when(tatuadorClient.obtenerTatuadorPorId(1L))
                .thenReturn(new TatuadorResponse());

        when(tatuajeClient.obtenerTatuajePorId(1L))
                .thenReturn(new TatuajeResponse());

        assertNotNull(sesionService.obtenerPorId(1L));
    }

    @Test
    void testGuardar() {

        SesionRequest request = new SesionRequest();

        request.setIdAyudante(1L);
        request.setIdCliente(1L);
        request.setIdEstudio(1L);
        request.setIdPago(1L);
        request.setIdTatuador(1L);
        request.setIdTatuaje(1L);

        request.setFechaSesion(LocalDate.now());
        request.setHoraSesion(LocalTime.of(10, 0));
        request.setDuracionHoras(3);

        SesionModel sesion = new SesionModel();
        sesion.setId(1L);

        when(ayudanteClient.obtenerAyudantePorId(1L))
                .thenReturn(new AyudanteResponse());

        when(clienteClient.obtenerClientePorId(1L))
                .thenReturn(new ClienteResponse());

        when(estudioClient.obtenerEstudioPorId(1L))
                .thenReturn(new EstudioResponse());

        when(pagoClient.obtenerPagoPorId(1L))
                .thenReturn(new PagoResponse());

        when(tatuadorClient.obtenerTatuadorPorId(1L))
                .thenReturn(new TatuadorResponse());

        when(tatuajeClient.obtenerTatuajePorId(1L))
                .thenReturn(new TatuajeResponse());

        when(sesionRepository.save(any(SesionModel.class)))
                .thenReturn(sesion);

        assertNotNull(sesionService.guardar(request));
    }

    @Test
    void testEliminar() {

        SesionModel sesion = new SesionModel();
        sesion.setId(1L);

        when(sesionRepository.findById(1L))
                .thenReturn(Optional.of(sesion));

        doNothing().when(sesionRepository)
                .delete(sesion);

        sesionService.eliminar(1L);

        verify(sesionRepository, times(1))
                .delete(sesion);
    }
}