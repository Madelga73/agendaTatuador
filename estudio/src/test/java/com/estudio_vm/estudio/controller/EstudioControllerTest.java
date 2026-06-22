package com.estudio_vm.estudio.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.estudio_vm.estudio.controllers.EstudioController;
import com.estudio_vm.estudio.models.EstudioModel;
import com.estudio_vm.estudio.services.EstudioService;
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

@WebMvcTest(EstudioController.class)
public class EstudioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstudioService estudioService;

    @Autowired
    private ObjectMapper objectMapper;

    private EstudioModel estudio;

    @BeforeEach
    void setUp() {

        estudio = new EstudioModel();

        estudio.setId(1L);
        estudio.setNombre("katarsis tattoo estudio");
        estudio.setUbicacion("san emilio 169, providencia, chile");
    }

    @Test
    public void testGetAllEstudios() throws Exception {

        when(estudioService.obtenerTodos())
                .thenReturn(List.of(estudio));

        mockMvc.perform(get("/api/v1/estudios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("katarsis tattoo estudio"))
                .andExpect(jsonPath("$[0].ubicacion").value("san emilio 169, providencia, chile"));
    }

    @Test
    public void testGetEstudioById() throws Exception {

        when(estudioService.obtenerPorId(1L))
                .thenReturn(Optional.of(estudio));

        mockMvc.perform(get("/api/v1/estudios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("katarsis tattoo estudio"))
                .andExpect(jsonPath("$.ubicacion").value("san emilio 169, providencia, chile"));
    }

    @Test
    public void testGetEstudioByNombre() throws Exception {

        when(estudioService.obtenerPorNombre("katarsis tattoo estudio"))
                .thenReturn(Optional.of(estudio));

        mockMvc.perform(get("/api/v1/estudios/nombre/katarsis tattoo estudio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("katarsis tattoo estudio"))
                .andExpect(jsonPath("$.ubicacion").value("san emilio 169, providencia, chile"));
    }

    @Test
    public void testCreateEstudio() throws Exception {

        when(estudioService.guardar(any(EstudioModel.class)))
                .thenReturn(estudio);

        mockMvc.perform(post("/api/v1/estudios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(estudio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("katarsis tattoo estudio"));
    }

    @Test
    public void testDeleteEstudio() throws Exception {

        doNothing().when(estudioService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/estudios/1"))
                .andExpect(status().isNoContent());

        verify(estudioService, times(1)).eliminar(1L);
    }
}