package com.inventario_vm.inventario.service;

import com.inventario_vm.inventario.client.TatuadorClient;
import com.inventario_vm.inventario.client.VendedorClient;
import com.inventario_vm.inventario.dtos.request.InventarioRequest;
import com.inventario_vm.inventario.dtos.response.InventarioResponse;
import com.inventario_vm.inventario.dtos.response.TatuadorResponse;
import com.inventario_vm.inventario.dtos.response.VendedorResponse;
import com.inventario_vm.inventario.models.InventarioModel;
import com.inventario_vm.inventario.repositories.InventarioRepository;
import com.inventario_vm.inventario.services.InventarioService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class InventarioServiceTest {

    @Autowired
    private InventarioService inventarioService;

    @MockBean
    private InventarioRepository inventarioRepository;

    @MockBean
    private TatuadorClient tatuadorClient;

    @MockBean
    private VendedorClient vendedorClient;

    @Test
    void testObtenerTodos() {

        InventarioModel inventario = new InventarioModel();

        inventario.setId(1L);
        inventario.setIdTatuador(1L);
        inventario.setIdVendedor(1L);
        inventario.setInsumo("tinta negra");
        inventario.setCantidad(20);
        inventario.setPrecioUnitario(15000);

        TatuadorResponse tatuador = new TatuadorResponse();
        VendedorResponse vendedor = new VendedorResponse();

        when(inventarioRepository.findAll())
                .thenReturn(List.of(inventario));

        when(tatuadorClient.obtenerTatuadorPorId(1L))
                .thenReturn(tatuador);

        when(vendedorClient.obtenerVendedorPorId(1L))
                .thenReturn(vendedor);

        List<InventarioResponse> resultado =
                inventarioService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void testObtenerPorId() {

        InventarioModel inventario = new InventarioModel();

        inventario.setId(1L);
        inventario.setIdTatuador(1L);
        inventario.setIdVendedor(1L);

        when(inventarioRepository.findById(1L))
                .thenReturn(Optional.of(inventario));

        when(tatuadorClient.obtenerTatuadorPorId(1L))
                .thenReturn(new TatuadorResponse());

        when(vendedorClient.obtenerVendedorPorId(1L))
                .thenReturn(new VendedorResponse());

        InventarioResponse response =
                inventarioService.obtenerPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testGuardar() {

        InventarioRequest request = new InventarioRequest();

        request.setIdTatuador(1L);
        request.setIdVendedor(1L);
        request.setInsumo("tinta negra");
        request.setCantidad(20);
        request.setPrecioUnitario(15000);

        InventarioModel inventario = new InventarioModel();

        inventario.setId(1L);
        inventario.setIdTatuador(1L);
        inventario.setIdVendedor(1L);
        inventario.setInsumo("tinta negra");
        inventario.setCantidad(20);
        inventario.setPrecioUnitario(15000);

        when(tatuadorClient.obtenerTatuadorPorId(1L))
                .thenReturn(new TatuadorResponse());

        when(vendedorClient.obtenerVendedorPorId(1L))
                .thenReturn(new VendedorResponse());

        when(inventarioRepository.save(any(InventarioModel.class)))
                .thenReturn(inventario);

        InventarioResponse response =
                inventarioService.guardar(request);

        assertNotNull(response);
        assertEquals("tinta negra", response.getInsumo());
    }

    @Test
    void testEliminar() {

        InventarioModel inventario = new InventarioModel();
        inventario.setId(1L);

        when(inventarioRepository.findById(1L))
                .thenReturn(Optional.of(inventario));

        doNothing().when(inventarioRepository)
                .delete(inventario);

        inventarioService.eliminar(1L);

        verify(inventarioRepository, times(1))
                .delete(inventario);
    }
}