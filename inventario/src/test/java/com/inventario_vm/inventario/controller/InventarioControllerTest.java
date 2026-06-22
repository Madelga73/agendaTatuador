package com.inventario_vm.inventario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventario_vm.inventario.controllers.InventarioController;
import com.inventario_vm.inventario.dtos.request.InventarioRequest;
import com.inventario_vm.inventario.dtos.response.InventarioResponse;
import com.inventario_vm.inventario.services.InventarioService;

import org.junit.jupiter.api.BeforeEach;
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

@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private InventarioResponse response;
    private InventarioRequest request;

    @BeforeEach
    void setUp() {

        response = InventarioResponse.builder()
                .id(1L)
                .idTatuador(1L)
                .idVendedor(1L)
                .insumo("tinta negra")
                .cantidad(20)
                .precioUnitario(15000)
                .build();

        request = new InventarioRequest();

        request.setIdTatuador(1L);
        request.setIdVendedor(1L);
        request.setInsumo("tinta negra");
        request.setCantidad(20);
        request.setPrecioUnitario(15000);
    }

    @Test
    void testListar() throws Exception {

        when(inventarioService.obtenerTodos())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/inventarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].insumo")
                        .value("tinta negra"));
    }

    @Test
    void testObtenerPorId() throws Exception {

        when(inventarioService.obtenerPorId(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/inventarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGuardar() throws Exception {

        when(inventarioService.guardar(any(InventarioRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/inventarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.insumo")
                        .value("tinta negra"));
    }

    @Test
    void testEliminar() throws Exception {

        doNothing().when(inventarioService)
                .eliminar(1L);

        mockMvc.perform(delete("/api/v1/inventarios/1"))
                .andExpect(status().isNoContent());

        verify(inventarioService, times(1))
                .eliminar(1L);
    }
}