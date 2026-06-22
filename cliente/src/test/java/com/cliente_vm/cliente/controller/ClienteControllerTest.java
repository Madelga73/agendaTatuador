package com.cliente_vm.cliente.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cliente_vm.cliente.controllers.ClienteController;
import com.cliente_vm.cliente.models.ClienteModel;
import com.cliente_vm.cliente.services.ClienteService;
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

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private ClienteModel cliente;

    @BeforeEach
    void setUp() {

        cliente = new ClienteModel();

        cliente.setId(1L);
        cliente.setRut("11.111.111-1");
        cliente.setNombre("Ana");
        cliente.setApellido("suarez");
        cliente.setTelefono("56932294444");
    }

    @Test
    public void testGetAllClientes() throws Exception {

        when(clienteService.obtenerTodos())
                .thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/v1/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].rut").value("11.111.111-1"))
                .andExpect(jsonPath("$[0].nombre").value("Ana"))
                .andExpect(jsonPath("$[0].apellido").value("suarez"))
                .andExpect(jsonPath("$[0].telefono").value("56932294444"));
    }

    @Test
    public void testGetClienteById() throws Exception {

        when(clienteService.obtenerPorId(1L))
                .thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/api/v1/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rut").value("11.111.111-1"))
                .andExpect(jsonPath("$.nombre").value("Ana"))
                .andExpect(jsonPath("$.apellido").value("suarez"))
                .andExpect(jsonPath("$.telefono").value("56932294444"));
    }

    @Test
    public void testGetClienteByRut() throws Exception {

        when(clienteService.obtenerPorRut("11.111.111-1"))
                .thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/api/v1/clientes/rut/11.111.111-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rut").value("11.111.111-1"))
                .andExpect(jsonPath("$.nombre").value("Ana"))
                .andExpect(jsonPath("$.apellido").value("suarez"))
                .andExpect(jsonPath("$.telefono").value("56932294444"));
    }

    @Test
    public void testCreateCliente() throws Exception {

        when(clienteService.guardar(any(ClienteModel.class)))
                .thenReturn(cliente);

        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rut").value("11.111.111-1"))
                .andExpect(jsonPath("$.nombre").value("Ana"));
    }

    @Test
    public void testDeleteCliente() throws Exception {

        doNothing().when(clienteService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/clientes/1"))
                .andExpect(status().isNoContent());

        verify(clienteService, times(1)).eliminar(1L);
    }
}