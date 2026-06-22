package com.pago_vm.pago.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pago_vm.pago.controllers.PagoController;
import com.pago_vm.pago.models.PagoModel;
import com.pago_vm.pago.services.PagoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@WebMvcTest(PagoController.class)
public class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagoService pagoService;

    @Autowired
    private ObjectMapper objectMapper;

    private PagoModel pago;

    @BeforeEach
    void setUp() {

        pago = new PagoModel();

        pago.setId(1L);
        pago.setNumBoleta("1001");
        pago.setMontoTotal(45000);
        pago.setMontoInsumos(12000);
        pago.setMetodoPago("efectivo");
    }

    @Test
    public void testGetAllPagos() throws Exception {

        when(pagoService.obtenerTodos())
                .thenReturn(List.of(pago));

        mockMvc.perform(get("/api/v1/pagos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].numBoleta").value("1001"))
                .andExpect(jsonPath("$[0].montoTotal").value(45000))
                .andExpect(jsonPath("$[0].montoInsumos").value(12000))
                .andExpect(jsonPath("$[0].metodoPago").value("efectivo"));
    }

    @Test
    public void testGetPagoById() throws Exception {

        when(pagoService.obtenerPorId(1L))
                .thenReturn(Optional.of(pago));

        mockMvc.perform(get("/api/v1/pagos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numBoleta").value("1001"))
                .andExpect(jsonPath("$.montoTotal").value(45000))
                .andExpect(jsonPath("$.montoInsumos").value(12000))
                .andExpect(jsonPath("$.metodoPago").value("efectivo"));
    }

    @Test
    public void testGetPagoByNumBoleta() throws Exception {

        when(pagoService.obtenerPorNumBoleta("1001"))
                .thenReturn(Optional.of(pago));

        mockMvc.perform(get("/api/v1/pagos/num_boleta/1001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numBoleta").value("1001"))
                .andExpect(jsonPath("$.montoTotal").value(45000))
                .andExpect(jsonPath("$.montoInsumos").value(12000))
                .andExpect(jsonPath("$.metodoPago").value("efectivo"));
    }

    @Test
    public void testCreatePago() throws Exception {

        when(pagoService.guardar(any(PagoModel.class)))
                .thenReturn(pago);

        mockMvc.perform(post("/api/v1/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pago)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numBoleta").value("1001"));
    }

    @Test
    public void testDeletePago() throws Exception {

        doNothing().when(pagoService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/pagos/1"))
                .andExpect(status().isNoContent());

        verify(pagoService, times(1)).eliminar(1L);
    }
}