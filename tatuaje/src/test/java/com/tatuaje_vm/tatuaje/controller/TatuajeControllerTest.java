package com.tatuaje_vm.tatuaje.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tatuaje_vm.tatuaje.controllers.TatuajeController;
import com.tatuaje_vm.tatuaje.models.TatuajeModel;
import com.tatuaje_vm.tatuaje.services.TatuajeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@WebMvcTest(TatuajeController.class)
public class TatuajeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TatuajeService tatuajeService;

    @Autowired
    private ObjectMapper objectMapper;

    private TatuajeModel tatuaje;

    @BeforeEach
    void setUp() {

        tatuaje = new TatuajeModel();

        tatuaje.setId(1L);
        tatuaje.setDescripcion("Dragon japones en brazo completo con detalles en negro y rojo");
        tatuaje.setZona("Brazo izquierdo");
        tatuaje.setEstilo("Japones");
    }

    @Test
    public void testGetAllTatuajes() throws Exception {

        when(tatuajeService.obtenerTodos())
                .thenReturn(List.of(tatuaje));

        mockMvc.perform(get("/api/v1/tatuajes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descripcion").value("Dragon japones en brazo completo con detalles en negro y rojo"))
                .andExpect(jsonPath("$[0].zona").value("Brazo izquierdo"))
                .andExpect(jsonPath("$[0].estilo").value("Japones"));
    }

    @Test
    public void testGetTatuajeById() throws Exception {

        when(tatuajeService.obtenerPorId(1L))
                .thenReturn(Optional.of(tatuaje));

        mockMvc.perform(get("/api/v1/tatuajes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Dragon japones en brazo completo con detalles en negro y rojo"))
                .andExpect(jsonPath("$.zona").value("Brazo izquierdo"))
                .andExpect(jsonPath("$.estilo").value("Japones"));
    }

    @Test
    public void testGetTatuajeByDescripcion() throws Exception {

        when(tatuajeService.obtenerPorDescripcion(
                "Dragon japones en brazo completo con detalles en negro y rojo"))
                .thenReturn(Optional.of(tatuaje));

        mockMvc.perform(get("/api/v1/tatuajes/descripcion/Dragon japones en brazo completo con detalles en negro y rojo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.zona").value("Brazo izquierdo"))
                .andExpect(jsonPath("$.estilo").value("Japones"));
    }

    @Test
    public void testCreateTatuaje() throws Exception {

        when(tatuajeService.guardar(any(TatuajeModel.class)))
                .thenReturn(tatuaje);

        mockMvc.perform(post("/api/v1/tatuajes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tatuaje)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estilo").value("Japones"));
    }

    @Test
    public void testDeleteTatuaje() throws Exception {

        doNothing().when(tatuajeService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/tatuajes/1"))
                .andExpect(status().isNoContent());

        verify(tatuajeService, times(1)).eliminar(1L);
    }
}