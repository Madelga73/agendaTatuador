package com.sesion_vm.sesion.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.sesion_vm.sesion.Services.SesionService;
import com.sesion_vm.sesion.controllers.SesionController;

import com.sesion_vm.sesion.dtos.request.SesionRequest;
import com.sesion_vm.sesion.dtos.response.SesionResponse;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SesionController.class)
public class SesionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SesionService sesionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListar() throws Exception {

        SesionResponse response = SesionResponse.builder()
                .id(1L)
                .idAyudante(1L)
                .idCliente(1L)
                .idEstudio(1L)
                .idPago(1L)
                .idTatuador(1L)
                .idTatuaje(1L)
                .build();

        when(sesionService.obtenerTodas())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/sesiones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testObtenerPorId() throws Exception {

        SesionResponse response = SesionResponse.builder()
                .id(1L)
                .idAyudante(1L)
                .idCliente(1L)
                .build();

        when(sesionService.obtenerPorId(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/sesiones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGuardar() throws Exception {

        SesionRequest request = new SesionRequest();

        request.setIdAyudante(1L);
        request.setIdCliente(1L);
        request.setIdEstudio(1L);
        request.setIdPago(1L);
        request.setIdTatuador(1L);
        request.setIdTatuaje(1L);
        request.setFechaSesion(LocalDate.of(2026, 4, 20));
        request.setHoraSesion(LocalTime.of(9, 30));
        request.setDuracionHoras(3);

        SesionResponse response = SesionResponse.builder()
                .id(1L)
                .build();

        when(sesionService.guardar(request))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/sesiones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void testEliminar() throws Exception {

        mockMvc.perform(delete("/api/v1/sesiones/1"))
                .andExpect(status().isNoContent());
    }
}
