package com.resultado_vm.resultado.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resultado_vm.resultado.dtos.request.ResultadoRequest;
import com.resultado_vm.resultado.dtos.response.ResultadoResponse;
import com.resultado_vm.resultado.services.ResultadoService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResultadoControllerTest.class)
public class ResultadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResultadoService resultadoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListar() throws Exception {

        ResultadoResponse resultado = ResultadoResponse.builder()
                .id(1L)
                .idCliente(1L)
                .idTatuaje(1L)
                .valoracion(8)
                .comentario("Excelente trabajo")
                .build();

        when(resultadoService.obtenerTodos())
                .thenReturn(List.of(resultado));

        mockMvc.perform(get("/api/v1/resultados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].valoracion").value(8));
    }

    @Test
    void testObtenerPorId() throws Exception {

        ResultadoResponse resultado = ResultadoResponse.builder()
                .id(1L)
                .idCliente(1L)
                .idTatuaje(1L)
                .valoracion(8)
                .comentario("Excelente trabajo")
                .build();

        when(resultadoService.obtenerPorId(1L))
                .thenReturn(resultado);

        mockMvc.perform(get("/api/v1/resultados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valoracion").value(8));
    }

    @Test
    void testGuardar() throws Exception {

        ResultadoRequest request = new ResultadoRequest();
        request.setIdCliente(1L);
        request.setIdTatuaje(1L);
        request.setValoracion(8);
        request.setComentario("Excelente trabajo");

        ResultadoResponse response = ResultadoResponse.builder()
                .id(1L)
                .idCliente(1L)
                .idTatuaje(1L)
                .valoracion(8)
                .comentario("Excelente trabajo")
                .build();

        when(resultadoService.guardar(any(ResultadoRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/resultados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.valoracion").value(8));
    }

    @Test
    void testActualizar() throws Exception {

        ResultadoRequest request = new ResultadoRequest();
        request.setIdCliente(1L);
        request.setIdTatuaje(1L);
        request.setValoracion(10);
        request.setComentario("Perfecto");

        ResultadoResponse response = ResultadoResponse.builder()
                .id(1L)
                .idCliente(1L)
                .idTatuaje(1L)
                .valoracion(10)
                .comentario("Perfecto")
                .build();

        when(resultadoService.actualizar(eq(1L), any(ResultadoRequest.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/v1/resultados/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valoracion").value(10));
    }

    @Test
    void testEliminar() throws Exception {

        doNothing().when(resultadoService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/resultados/1"))
                .andExpect(status().isNoContent());

        verify(resultadoService, times(1))
                .eliminar(1L);
    }
}