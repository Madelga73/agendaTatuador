package com.inventario_vm.inventario.services;

import com.inventario_vm.inventario.repositories.InventarioRepository;

import com.inventario_vm.inventario.client.TatuadorClient;
import com.inventario_vm.inventario.client.VendedorClient;

import com.inventario_vm.inventario.dtos.request.InventarioRequest;

import com.inventario_vm.inventario.dtos.response.InventarioResponse;
import com.inventario_vm.inventario.dtos.response.TatuadorResponse;
import com.inventario_vm.inventario.dtos.response.VendedorResponse;

import com.inventario_vm.inventario.exceptions.NotFoundException;
import com.inventario_vm.inventario.exceptions.RemoteServiceException;

import com.inventario_vm.inventario.models.InventarioModel;

import feign.FeignException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    private final TatuadorClient tatuadorClient;
    private final VendedorClient vendedorClient;

    public InventarioService(
            InventarioRepository inventarioRepository,
            TatuadorClient tatuadorClient,
            VendedorClient vendedorClient) {

        this.inventarioRepository = inventarioRepository;

        this.tatuadorClient = tatuadorClient;
        this.vendedorClient = vendedorClient;
    }

    public List<InventarioResponse> obtenerTodos() {

        return inventarioRepository.findAll()
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
    }

    public InventarioResponse obtenerPorId(Long id) {

        InventarioModel inventario = inventarioRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe el inventario con id: " + id));

        return mapToResponseCompleta(inventario);
    }

    public List<InventarioResponse> obtenerPorTatuador(Long idTatuador) {

        return inventarioRepository.findByIdTatuador(idTatuador)
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
    }

    public List<InventarioResponse> obtenerPorVendedor(Long idVendedor) {

        return inventarioRepository.findByIdVendedor(idVendedor)
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
    }

    public InventarioResponse guardar(InventarioRequest request) {

        TatuadorResponse tatuador =
                obtenerTatuadorDesdeServicio(request.getIdTatuador());

        VendedorResponse vendedor =
                obtenerVendedorDesdeServicio(request.getIdVendedor());

        InventarioModel inventario = new InventarioModel();

        inventario.setIdTatuador(request.getIdTatuador());
        inventario.setIdVendedor(request.getIdVendedor());

        inventario.setInsumo(request.getInsumo());
        inventario.setCantidad(request.getCantidad());
        inventario.setPrecioUnitario(request.getPrecioUnitario());

        InventarioModel guardado = inventarioRepository.save(inventario);

        return mapToResponse(
                guardado,
                tatuador,
                vendedor
        );
    }

    public InventarioResponse actualizar(Long id, InventarioRequest request) {

        InventarioModel inventario = inventarioRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe el inventario con id: " + id));

        TatuadorResponse tatuador =
                obtenerTatuadorDesdeServicio(request.getIdTatuador());

        VendedorResponse vendedor =
                obtenerVendedorDesdeServicio(request.getIdVendedor());

        inventario.setIdTatuador(request.getIdTatuador());
        inventario.setIdVendedor(request.getIdVendedor());

        inventario.setInsumo(request.getInsumo());
        inventario.setCantidad(request.getCantidad());
        inventario.setPrecioUnitario(request.getPrecioUnitario());

        InventarioModel actualizado = inventarioRepository.save(inventario);

        return mapToResponse(
                actualizado,
                tatuador,
                vendedor
        );
    }

    public void eliminar(Long id) {

        InventarioModel inventario = inventarioRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe el inventario con id: " + id));

        inventarioRepository.delete(inventario);
    }

    private TatuadorResponse obtenerTatuadorDesdeServicio(Long idTatuador) {

        try {
            return tatuadorClient.obtenerTatuadorPorId(idTatuador);

        } catch (FeignException.NotFound e) {

            throw new NotFoundException(
                    "No existe el tatuador con id: " + idTatuador);

        } catch (FeignException e) {

            throw new RemoteServiceException(
                    "Error al comunicarse con el microservicio de tatuadores");
        }
    }

    private VendedorResponse obtenerVendedorDesdeServicio(Long idVendedor) {

        try {
            return vendedorClient.obtenerVendedorPorId(idVendedor);

        } catch (FeignException.NotFound e) {

            throw new NotFoundException(
                    "No existe el vendedor con id: " + idVendedor);

        } catch (FeignException e) {

            throw new RemoteServiceException(
                    "Error al comunicarse con el microservicio de vendedores");
        }
    }

    private InventarioResponse mapToResponseCompleta(InventarioModel inventario) {

        TatuadorResponse tatuador =
                obtenerTatuadorDesdeServicio(inventario.getIdTatuador());

        VendedorResponse vendedor =
                obtenerVendedorDesdeServicio(inventario.getIdVendedor());

        return mapToResponse(
                inventario,
                tatuador,
                vendedor
        );
    }

    private InventarioResponse mapToResponse(
            InventarioModel inventario,
            TatuadorResponse tatuador,
            VendedorResponse vendedor) {

        return InventarioResponse.builder()

                .id(inventario.getId())

                .idTatuador(inventario.getIdTatuador())
                .idVendedor(inventario.getIdVendedor())

                .insumo(inventario.getInsumo())
                .cantidad(inventario.getCantidad())
                .precioUnitario(inventario.getPrecioUnitario())

                .tatuador(tatuador)
                .vendedor(vendedor)

                .build();
    }
}
