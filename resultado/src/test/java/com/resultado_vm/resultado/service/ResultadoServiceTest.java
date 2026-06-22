package com.resultado_vm.resultado.service;

import com.resultado_vm.resultado.client.ClienteClient;
import com.resultado_vm.resultado.client.TatuajeClient;
import com.resultado_vm.resultado.dtos.request.ResultadoRequest;
import com.resultado_vm.resultado.dtos.response.ClienteResponse;
import com.resultado_vm.resultado.dtos.response.TatuajeResponse;
import com.resultado_vm.resultado.models.ResultadoModel;
import com.resultado_vm.resultado.repositories.ResultadoRepository;
import com.resultado_vm.resultado.services.ResultadoService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ResultadoServiceTest {

    @Autowired
    private ResultadoService resultadoService;

    @MockBean
    private ResultadoRepository resultadoRepository;

    @MockBean
    private ClienteClient clienteClient;

    @MockBean
    private TatuajeClient tatuajeClient;

    @Test
    void testObtenerTodos() {

        ResultadoModel resultado = new ResultadoModel();
        resultado.setId(1L);
        resultado.setIdCliente(1L);
        resultado.setIdTatuaje(1L);
        resultado.setValoracion(8);
        resultado.setComentario("Excelente");

        when(resultadoRepository.findAll())
                .thenReturn(List.of(resultado));

        when(clienteClient.obtenerClientePorId(1L))
                .thenReturn(new ClienteResponse());

        when(tatuajeClient.obtenerTatuajePorId(1L))
                .thenReturn(new TatuajeResponse());

        assertEquals(1, resultadoService.obtenerTodos().size());
    }

    @Test
    void testObtenerPorId() {

        ResultadoModel resultado = new ResultadoModel();
        resultado.setId(1L);
        resultado.setIdCliente(1L);
        resultado.setIdTatuaje(1L);
        resultado.setValoracion(8);

        when(resultadoRepository.findById(1L))
                .thenReturn(Optional.of(resultado));

        when(clienteClient.obtenerClientePorId(1L))
                .thenReturn(new ClienteResponse());

        when(tatuajeClient.obtenerTatuajePorId(1L))
                .thenReturn(new TatuajeResponse());

        assertNotNull(resultadoService.obtenerPorId(1L));
    }

    @Test
    void testGuardar() {

        ResultadoRequest request = new ResultadoRequest();
        request.setIdCliente(1L);
        request.setIdTatuaje(1L);
        request.setValoracion(10);
        request.setComentario("Excelente trabajo");

        ClienteResponse cliente = new ClienteResponse();
        TatuajeResponse tatuaje = new TatuajeResponse();

        ResultadoModel resultado = new ResultadoModel();
        resultado.setId(1L);
        resultado.setIdCliente(1L);
        resultado.setIdTatuaje(1L);
        resultado.setValoracion(10);
        resultado.setComentario("Excelente trabajo");

        when(clienteClient.obtenerClientePorId(1L))
                .thenReturn(cliente);

        when(tatuajeClient.obtenerTatuajePorId(1L))
                .thenReturn(tatuaje);

        when(resultadoRepository.save(any(ResultadoModel.class)))
                .thenReturn(resultado);

        assertNotNull(resultadoService.guardar(request));
    }

    @Test
    void testEliminar() {

        ResultadoModel resultado = new ResultadoModel();
        resultado.setId(1L);

        when(resultadoRepository.findById(1L))
                .thenReturn(Optional.of(resultado));

        doNothing().when(resultadoRepository)
                .delete(resultado);

        resultadoService.eliminar(1L);

        verify(resultadoRepository, times(1))
                .delete(resultado);
    }
}