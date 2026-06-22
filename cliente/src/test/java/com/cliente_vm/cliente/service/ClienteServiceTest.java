package com.cliente_vm.cliente.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.cliente_vm.cliente.models.ClienteModel;
import com.cliente_vm.cliente.repositories.ClienteRepository;
import com.cliente_vm.cliente.services.ClienteService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void testObtenerTodos() {

        ClienteModel cliente = new ClienteModel();

        cliente.setId(1L);
        cliente.setRut("11.111.111-1");
        cliente.setNombre("Ana");
        cliente.setApellido("suarez");
        cliente.setTelefono("56932294444");

        when(clienteRepository.findAll())
                .thenReturn(List.of(cliente));

        List<ClienteModel> clientes =
                clienteService.obtenerTodos();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    @Test
    public void testObtenerPorId() {

        Long id = 1L;

        ClienteModel cliente = new ClienteModel();

        cliente.setId(id);
        cliente.setRut("11.111.111-1");
        cliente.setNombre("Ana");
        cliente.setApellido("suarez");
        cliente.setTelefono("56932294444");

        when(clienteRepository.findById(id))
                .thenReturn(Optional.of(cliente));

        Optional<ClienteModel> encontrado =
                clienteService.obtenerPorId(id);

        assertTrue(encontrado.isPresent());
        assertEquals(id, encontrado.get().getId());
    }

    @Test
    public void testObtenerPorRut() {

        String rut = "11.111.111-1";

        ClienteModel cliente = new ClienteModel();

        cliente.setId(1L);
        cliente.setRut(rut);
        cliente.setNombre("Ana");
        cliente.setApellido("suarez");
        cliente.setTelefono("56932294444");

        when(clienteRepository.findByRut(rut))
                .thenReturn(Optional.of(cliente));

        Optional<ClienteModel> encontrado =
                clienteService.obtenerPorRut(rut);

        assertTrue(encontrado.isPresent());
        assertEquals(rut, encontrado.get().getRut());
    }

    @Test
    public void testGuardar() {

        ClienteModel cliente = new ClienteModel();

        cliente.setId(1L);
        cliente.setRut("11.111.111-1");
        cliente.setNombre("Ana");
        cliente.setApellido("suarez");
        cliente.setTelefono("56932294444");

        when(clienteRepository.save(cliente))
                .thenReturn(cliente);

        ClienteModel guardado =
                clienteService.guardar(cliente);

        assertNotNull(guardado);
        assertEquals("Ana", guardado.getNombre());
    }

    @Test
    public void testEliminar() {

        Long id = 1L;

        doNothing().when(clienteRepository)
                .deleteById(id);

        clienteService.eliminar(id);

        verify(clienteRepository, times(1))
                .deleteById(id);
    }
}