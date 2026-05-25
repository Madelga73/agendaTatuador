package com.sesion_vm.sesion.Services;

import com.sesion_vm.sesion.Repositories.SesionRepository;
import com.sesion_vm.sesion.client.AyudanteClient;
import com.sesion_vm.sesion.client.ClienteClient;
import com.sesion_vm.sesion.client.EstudioClient;
import com.sesion_vm.sesion.client.PagoClient;
import com.sesion_vm.sesion.client.TatuadorClient;
import com.sesion_vm.sesion.client.TatuajeClient;

import com.sesion_vm.sesion.dtos.request.SesionRequest;

import com.sesion_vm.sesion.dtos.response.AyudanteResponse;
import com.sesion_vm.sesion.dtos.response.ClienteResponse;
import com.sesion_vm.sesion.dtos.response.EstudioResponse;
import com.sesion_vm.sesion.dtos.response.PagoResponse;
import com.sesion_vm.sesion.dtos.response.SesionResponse;
import com.sesion_vm.sesion.dtos.response.TatuadorResponse;
import com.sesion_vm.sesion.dtos.response.TatuajeResponse;

import com.sesion_vm.sesion.exceptions.NotFoundException;
import com.sesion_vm.sesion.exceptions.RemoteServiceException;

import com.sesion_vm.sesion.models.SesionModel;

import feign.FeignException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SesionService {

    private final SesionRepository sesionRepository;

    private final AyudanteClient ayudanteClient;
    private final ClienteClient clienteClient;
    private final EstudioClient estudioClient;
    private final PagoClient pagoClient;
    private final TatuadorClient tatuadorClient;
    private final TatuajeClient tatuajeClient;

    public SesionService(
        SesionRepository sesionRepository,
        AyudanteClient ayudanteClient,
        ClienteClient clienteClient,
        EstudioClient estudioClient,
        PagoClient pagoClient,
        TatuadorClient tatuadorClient,
        TatuajeClient tatuajeClient) {

        this.sesionRepository = sesionRepository;

        this.ayudanteClient = ayudanteClient;
        this.clienteClient = clienteClient;
        this.estudioClient = estudioClient;
        this.pagoClient = pagoClient;
        this.tatuadorClient = tatuadorClient;
        this.tatuajeClient = tatuajeClient;
    }

    public List<SesionResponse> obtenerTodas() {

        return sesionRepository.findAll()
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
    }

    public SesionResponse obtenerPorId(Long id) {

        SesionModel sesion = sesionRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe la sesion con id: " + id));

        return mapToResponseCompleta(sesion);
    }

    public List<SesionResponse> obtenerPorAyudante(Long idAyudante) {

        return sesionRepository.findByIdAyudante(idAyudante)
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
    }

    public List<SesionResponse> obtenerPorCliente(Long idCliente) {

        return sesionRepository.findByIdCliente(idCliente)
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
        }

        public List<SesionResponse> obtenerPorEstudio(Long idEstudio) {

        return sesionRepository.findByIdEstudio(idEstudio)
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
        }

        public List<SesionResponse> obtenerPorPago(Long idPago) {

        return sesionRepository.findByIdPago(idPago)
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
        }

        public List<SesionResponse> obtenerPorTatuador(Long idTatuador) {

        return sesionRepository.findByIdTatuador(idTatuador)
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
        }

        public List<SesionResponse> obtenerPorTatuaje(Long idTatuaje) {

        return sesionRepository.findByIdTatuaje(idTatuaje)
                .stream()
                .map(this::mapToResponseCompleta)
                .toList();
        }

    public SesionResponse guardar(SesionRequest request) {

        AyudanteResponse ayudante =
                obtenerAyudanteDesdeServicio(request.getIdAyudante());

        ClienteResponse cliente =
                obtenerClienteDesdeServicio(request.getIdCliente());

        EstudioResponse estudio =
                obtenerEstudioDesdeServicio(request.getIdEstudio());

        PagoResponse pago =
                obtenerPagoDesdeServicio(request.getIdPago());

        TatuadorResponse tatuador =
                obtenerTatuadorDesdeServicio(request.getIdTatuador());

        TatuajeResponse tatuaje =
                obtenerTatuajeDesdeServicio(request.getIdTatuaje());

        SesionModel sesion = new SesionModel();

        sesion.setIdAyudante(request.getIdAyudante());
        sesion.setIdCliente(request.getIdCliente());
        sesion.setIdEstudio(request.getIdEstudio());
        sesion.setIdPago(request.getIdPago());
        sesion.setIdTatuador(request.getIdTatuador());
        sesion.setIdTatuaje(request.getIdTatuaje());

        sesion.setFechaSesion(request.getFechaSesion());
        sesion.setHoraSesion(request.getHoraSesion());
        sesion.setDuracionHoras(request.getDuracionHoras());

        SesionModel guardada = sesionRepository.save(sesion);

        return mapToResponse(
                guardada,
                ayudante,
                cliente,
                estudio,
                pago,
                tatuador,
                tatuaje
        );
    }

    public SesionResponse actualizar(Long id, SesionRequest request) {

        SesionModel sesion = sesionRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe la sesion con id: " + id));

        AyudanteResponse ayudante =
                obtenerAyudanteDesdeServicio(request.getIdAyudante());

        ClienteResponse cliente =
                obtenerClienteDesdeServicio(request.getIdCliente());

        EstudioResponse estudio =
                obtenerEstudioDesdeServicio(request.getIdEstudio());

        PagoResponse pago =
                obtenerPagoDesdeServicio(request.getIdPago());

        TatuadorResponse tatuador =
                obtenerTatuadorDesdeServicio(request.getIdTatuador());

        TatuajeResponse tatuaje =
                obtenerTatuajeDesdeServicio(request.getIdTatuaje());

        sesion.setIdAyudante(request.getIdAyudante());
        sesion.setIdCliente(request.getIdCliente());
        sesion.setIdEstudio(request.getIdEstudio());
        sesion.setIdPago(request.getIdPago());
        sesion.setIdTatuador(request.getIdTatuador());
        sesion.setIdTatuaje(request.getIdTatuaje());

        sesion.setFechaSesion(request.getFechaSesion());
        sesion.setHoraSesion(request.getHoraSesion());
        sesion.setDuracionHoras(request.getDuracionHoras());

        SesionModel actualizada = sesionRepository.save(sesion);

        return mapToResponse(
                actualizada,
                ayudante,
                cliente,
                estudio,
                pago,
                tatuador,
                tatuaje
        );
    }

    public void eliminar(Long id) {

        SesionModel sesion = sesionRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe la sesion con id: " + id));

        sesionRepository.delete(sesion);
    }

    private AyudanteResponse obtenerAyudanteDesdeServicio(Long idAyudante) {

        try {
            return ayudanteClient.obtenerAyudantePorId(idAyudante);

        } catch (FeignException.NotFound e) {

            throw new NotFoundException(
                    "No existe el ayudante con id: " + idAyudante);

        } catch (FeignException e) {

            throw new RemoteServiceException(
                    "Error al comunicarse con el microservicio de ayudantes");
        }
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

    private EstudioResponse obtenerEstudioDesdeServicio(Long idEstudio) {

        try {
            return estudioClient.obtenerEstudioPorId(idEstudio);

        } catch (FeignException.NotFound e) {

            throw new NotFoundException(
                    "No existe el estudio con id: " + idEstudio);

        } catch (FeignException e) {

            throw new RemoteServiceException(
                    "Error al comunicarse con el microservicio de estudios");
        }
    }

    private PagoResponse obtenerPagoDesdeServicio(Long idPago) {

        try {
            return pagoClient.obtenerPagoPorId(idPago);

        } catch (FeignException.NotFound e) {

            throw new NotFoundException(
                    "No existe el pago con id: " + idPago);

        } catch (FeignException e) {

            throw new RemoteServiceException(
                    "Error al comunicarse con el microservicio de pagos");
        }
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

    private SesionResponse mapToResponseCompleta(SesionModel sesion) {

        AyudanteResponse ayudante =
                obtenerAyudanteDesdeServicio(sesion.getIdAyudante());

        ClienteResponse cliente =
                obtenerClienteDesdeServicio(sesion.getIdCliente());

        EstudioResponse estudio =
                obtenerEstudioDesdeServicio(sesion.getIdEstudio());

        PagoResponse pago =
                obtenerPagoDesdeServicio(sesion.getIdPago());

        TatuadorResponse tatuador =
                obtenerTatuadorDesdeServicio(sesion.getIdTatuador());

        TatuajeResponse tatuaje =
                obtenerTatuajeDesdeServicio(sesion.getIdTatuaje());

        return mapToResponse(
                sesion,
                ayudante,
                cliente,
                estudio,
                pago,
                tatuador,
                tatuaje
        );
    }

    private SesionResponse mapToResponse(
            SesionModel sesion,
            AyudanteResponse ayudante,
            ClienteResponse cliente,
            EstudioResponse estudio,
            PagoResponse pago,
            TatuadorResponse tatuador,
            TatuajeResponse tatuaje) {

        return SesionResponse.builder()

                .id(sesion.getId())

                .idAyudante(sesion.getIdAyudante())
                .idCliente(sesion.getIdCliente())
                .idEstudio(sesion.getIdEstudio())
                .idPago(sesion.getIdPago())
                .idTatuador(sesion.getIdTatuador())
                .idTatuaje(sesion.getIdTatuaje())

                .fechaSesion(sesion.getFechaSesion())
                .horaSesion(sesion.getHoraSesion())
                .duracionHoras(sesion.getDuracionHoras())

                .ayudante(ayudante)
                .cliente(cliente)
                .estudio(estudio)
                .pago(pago)
                .tatuador(tatuador)
                .tatuaje(tatuaje)

                .build();
    }
}