package com.ayudante_vm.ayudante.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ayudante_vm.ayudante.controllers.AyudanteController;
import com.ayudante_vm.ayudante.models.AyudanteModel;
import com.ayudante_vm.ayudante.services.AyudanteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@WebMvcTest(AyudanteController.class)
public class AyudanteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AyudanteService ayudanteService;

    @Autowired
    private ObjectMapper objectMapper;

    private AyudanteModel ayudante;

    @BeforeEach
    void setUp() {

        ayudante = new AyudanteModel();

        ayudante.setId(1L);
        ayudante.setRut("34.111.111-1");
        ayudante.setNombre("lara");
        ayudante.setApellido("ibañez");
        ayudante.setTelefono("56989264826");
    }

    @Test
    public void testGetAllAyudantes() throws Exception {

        when(ayudanteService.obtenerTodos())
                .thenReturn(List.of(ayudante));

        mockMvc.perform(get("/api/v1/ayudantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].rut").value("34.111.111-1"))
                .andExpect(jsonPath("$[0].nombre").value("lara"))
                .andExpect(jsonPath("$[0].apellido").value("ibañez"))
                .andExpect(jsonPath("$[0].telefono").value("56989264826"));
    }

    @Test
    public void testGetAyudanteById() throws Exception {

        when(ayudanteService.obtenerPorId(1L))
                .thenReturn(Optional.of(ayudante));

        mockMvc.perform(get("/api/v1/ayudantes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rut").value("34.111.111-1"))
                .andExpect(jsonPath("$.nombre").value("lara"))
                .andExpect(jsonPath("$.apellido").value("ibañez"))
                .andExpect(jsonPath("$.telefono").value("56989264826"));
    }

    @Test
    public void testGetAyudanteByRut() throws Exception {

        when(ayudanteService.obtenerPorRut("34.111.111-1"))
                .thenReturn(Optional.of(ayudante));

        mockMvc.perform(get("/api/v1/ayudantes/rut/34.111.111-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rut").value("34.111.111-1"))
                .andExpect(jsonPath("$.nombre").value("lara"))
                .andExpect(jsonPath("$.apellido").value("ibañez"))
                .andExpect(jsonPath("$.telefono").value("56989264826"));
    }

    @Test
    public void testCreateAyudante() throws Exception {

        when(ayudanteService.guardar(any(AyudanteModel.class)))
                .thenReturn(ayudante);

        mockMvc.perform(post("/api/v1/ayudantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ayudante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rut").value("34.111.111-1"))
                .andExpect(jsonPath("$.nombre").value("lara"));
    }

    @Test
    public void testDeleteAyudante() throws Exception {

        doNothing().when(ayudanteService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/ayudantes/1"))
                .andExpect(status().isNoContent());

        verify(ayudanteService, times(1)).eliminar(1L);
    }
}