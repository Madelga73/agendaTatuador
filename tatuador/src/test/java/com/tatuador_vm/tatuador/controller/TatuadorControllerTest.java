package com.tatuador_vm.tatuador.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tatuador_vm.tatuador.controllers.TatuadorController;
import com.tatuador_vm.tatuador.models.TatuadorModel;
import com.tatuador_vm.tatuador.services.TatuadorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@WebMvcTest(TatuadorController.class)
public class TatuadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TatuadorService tatuadorService;

    @Autowired
    private ObjectMapper objectMapper;

    private TatuadorModel tatuador;

    @BeforeEach
    void setUp() {

        tatuador = new TatuadorModel();

        tatuador.setId(1L);
        tatuador.setRut("14.256.789-3");
        tatuador.setNombre("Matias");
        tatuador.setApellido("Suarez");
        tatuador.setEspecialidad("Blackwork");
        tatuador.setTelefono("569325435441");
    }

    @Test
    public void testGetAllTatuadores() throws Exception {

        when(tatuadorService.obtenerTodos())
                .thenReturn(List.of(tatuador));

        mockMvc.perform(get("/api/v1/tatuadores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].rut").value("14.256.789-3"))
                .andExpect(jsonPath("$[0].nombre").value("Matias"))
                .andExpect(jsonPath("$[0].apellido").value("Suarez"))
                .andExpect(jsonPath("$[0].especialidad").value("Blackwork"))
                .andExpect(jsonPath("$[0].telefono").value("569325435441"));
    }

    @Test
    public void testGetTatuadorById() throws Exception {

        when(tatuadorService.obtenerPorId(1L))
                .thenReturn(Optional.of(tatuador));

        mockMvc.perform(get("/api/v1/tatuadores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rut").value("14.256.789-3"))
                .andExpect(jsonPath("$.nombre").value("Matias"));
    }

    @Test
    public void testGetTatuadorByRut() throws Exception {

        when(tatuadorService.obtenerPorRut("14.256.789-3"))
                .thenReturn(Optional.of(tatuador));

        mockMvc.perform(get("/api/v1/tatuadores/rut/14.256.789-3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("14.256.789-3"))
                .andExpect(jsonPath("$.especialidad").value("Blackwork"));
    }

    @Test
    public void testCreateTatuador() throws Exception {

        when(tatuadorService.guardar(any(TatuadorModel.class)))
                .thenReturn(tatuador);

        mockMvc.perform(post("/api/v1/tatuadores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tatuador)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Matias"));
    }

    @Test
    public void testDeleteTatuador() throws Exception {

        doNothing().when(tatuadorService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/tatuadores/1"))
                .andExpect(status().isNoContent());

        verify(tatuadorService, times(1)).eliminar(1L);
    }
}