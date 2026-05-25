package com.resultado_vm.resultado.services;

import com.resultado_vm.resultado.repositories.ResultadoRepository;

import com.resultado_vm.resultado.client.ClienteClient;
import com.resultado_vm.resultado.client.TatuajeClient;

import com.resultado_vm.resultado.dtos.request.ResultadoRequest;

import com.resultado_vm.resultado.dtos.response.ResultadoResponse;
import com.resultado_vm.resultado.dtos.response.ClienteResponse;
import com.resultado_vm.resultado.dtos.response.TatuajeResponse;

import com.resultado_vm.resultado.exceptions.NotFoundException;
import com.resultado_vm.resultado.exceptions.RemoteServiceException;

import com.resultado_vm.resultado.models.ResultadoModel;

import feign.FeignException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResultadoService {

    private final ResultadoRepository resultadoRepository;

    private final ClienteClient clienteClient;
    private final TatuajeClient tatuajeClient;

    public ResultadoService(
            ResultadoRepository resultadoRepository,
            ClienteClient clienteClient,
            TatuajeClient tatuajeClient) {

        this.resultadoRepository = resultadoRepository;

        this.clienteClient = clienteClient;
        this.tatuajeClient = tatuajeClient;
    }

    public List<ResultadoResponse> obtenerTodos() {

        return resultadoRepository.findAll()
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
    }

    public ResultadoResponse obtenerPorId(Long id) {

        ResultadoModel resultado = resultadoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe el resultado con id: " + id));

        return mapToResponseCompleta(resultado);
    }

    public List<ResultadoResponse> obtenerPorCliente(Long idCliente) {

        return resultadoRepository.findByIdCliente(idCliente)
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
    }

    public List<ResultadoResponse> obtenerPorTatuaje(Long idTatuaje) {

        return resultadoRepository.findByIdTatuaje(idTatuaje)
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
    }

    public ResultadoResponse guardar(ResultadoRequest request) {

        ClienteResponse cliente =
                obtenerClienteDesdeServicio(request.getIdCliente());

        TatuajeResponse tatuaje =
                obtenerTatuajeDesdeServicio(request.getIdTatuaje());

        ResultadoModel resultado = new ResultadoModel();

        resultado.setIdCliente(request.getIdCliente());
        resultado.setIdTatuaje(request.getIdTatuaje());

        resultado.setValoracion(request.getValoracion());
        resultado.setComentario(request.getComentario());

        ResultadoModel guardado = resultadoRepository.save(resultado);

        return mapToResponse(
                guardado,
                cliente,
                tatuaje
        );
    }

    public ResultadoResponse actualizar(Long id, ResultadoRequest request) {

        ResultadoModel resultado = resultadoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe el resultado con id: " + id));

        ClienteResponse cliente =
                obtenerClienteDesdeServicio(request.getIdCliente());

        TatuajeResponse tatuaje =
                obtenerTatuajeDesdeServicio(request.getIdTatuaje());

        resultado.setIdCliente(request.getIdCliente());
        resultado.setIdTatuaje(request.getIdTatuaje());

        resultado.setValoracion(request.getValoracion());
        resultado.setComentario(request.getComentario());

        ResultadoModel actualizado = resultadoRepository.save(resultado);

        return mapToResponse(
                actualizado,
                cliente,
                tatuaje
        );
    }

    public void eliminar(Long id) {

        ResultadoModel resultado = resultadoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe el resultado con id: " + id));

        resultadoRepository.delete(resultado);
    }

    private ClienteResponse obtenerClienteDesdeServicio(Long idCliente) {

        try {
            return clienteClient.obtenerClientePorId(idCliente);

        } catch (FeignException.NotFound e) {

            throw new NotFoundException(
                    "No existe el cliente con id: " + idCliente);

        } catch (FeignException e) {

            throw new RemoteServiceException(
                    "Error al comunicarse con el microservicio de clientes");
        }
    }

    private TatuajeResponse obtenerTatuajeDesdeServicio(Long idTatuaje) {

        try {
            return tatuajeClient.obtenerTatuajePorId(idTatuaje);

        } catch (FeignException.NotFound e) {

            throw new NotFoundException(
                    "No existe el tatuaje con id: " + idTatuaje);

        } catch (FeignException e) {

            throw new RemoteServiceException(
                    "Error al comunicarse con el microservicio de tatuajes");
        }
    }

    private ResultadoResponse mapToResponseCompleta(ResultadoModel resultado) {

        ClienteResponse cliente =
                obtenerClienteDesdeServicio(resultado.getIdCliente());

        TatuajeResponse tatuaje =
                obtenerTatuajeDesdeServicio(resultado.getIdTatuaje());

        return mapToResponse(
                resultado,
                cliente,
                tatuaje
        );
    }

    private ResultadoResponse mapToResponse(
            ResultadoModel resultado,
            ClienteResponse cliente,
            TatuajeResponse tatuaje) {

        return ResultadoResponse.builder()

                .id(resultado.getId())

                .idCliente(resultado.getIdCliente())
                .idTatuaje(resultado.getIdTatuaje())

                .valoracion(resultado.getValoracion())
                .comentario(resultado.getComentario())

                .cliente(cliente)
                .tatuaje(tatuaje)

                .build();
    }
}