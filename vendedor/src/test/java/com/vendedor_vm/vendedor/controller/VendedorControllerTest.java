package com.vendedor_vm.vendedor.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendedor_vm.vendedor.controllers.VendedorController;
import com.vendedor_vm.vendedor.models.VendedorModel;
import com.vendedor_vm.vendedor.services.VendedorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@WebMvcTest(VendedorController.class)
public class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendedorService vendedorService;

    @Autowired
    private ObjectMapper objectMapper;

    private VendedorModel vendedor;

    @BeforeEach
    void setUp() {

        vendedor = new VendedorModel();

        vendedor.setId(1L);
        vendedor.setNombre("makuza tattoo supply");
        vendedor.setUbicacion("millers 289, providencia, chile");
    }

    @Test
    public void testGetAllVendedores() throws Exception {

        when(vendedorService.obtenerTodos())
                .thenReturn(List.of(vendedor));

        mockMvc.perform(get("/api/v1/vendedores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("makuza tattoo supply"))
                .andExpect(jsonPath("$[0].ubicacion").value("millers 289, providencia, chile"));
    }

    @Test
    public void testGetVendedorById() throws Exception {

        when(vendedorService.obtenerPorId(1L))
                .thenReturn(Optional.of(vendedor));

        mockMvc.perform(get("/api/v1/vendedores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("makuza tattoo supply"))
                .andExpect(jsonPath("$.ubicacion").value("millers 289, providencia, chile"));
    }

    @Test
    public void testGetVendedorByNombre() throws Exception {

        when(vendedorService.obtenerPorNombre("makuza tattoo supply"))
                .thenReturn(Optional.of(vendedor));

        mockMvc.perform(get("/api/v1/vendedores/nombre/makuza tattoo supply"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("makuza tattoo supply"))
                .andExpect(jsonPath("$.ubicacion").value("millers 289, providencia, chile"));
    }

    @Test
    public void testCreateVendedor() throws Exception {

        when(vendedorService.guardar(any(VendedorModel.class)))
                .thenReturn(vendedor);

        mockMvc.perform(post("/api/v1/vendedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vendedor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("makuza tattoo supply"));
    }

    @Test
    public void testDeleteVendedor() throws Exception {

        doNothing().when(vendedorService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/vendedores/1"))
                .andExpect(status().isNoContent());

        verify(vendedorService, times(1)).eliminar(1L);
    }
}
