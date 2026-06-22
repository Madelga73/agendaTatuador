package com.pago_vm.pago.services;
import com.pago_vm.pago.models.PagoModel;
import com.pago_vm.pago.repositories.PagoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PagoService {
    private final PagoRepository pagoRepository;
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<PagoModel> obtenerTodos(){
        return pagoRepository.findAll();
    }

    public Optional<PagoModel> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Optional<PagoModel> obtenerPorNumBoleta(String numBoleta) {
        return pagoRepository.findByNumBoleta(numBoleta);
    }

    public PagoModel guardar(PagoModel pago) {
        return pagoRepository.save(pago);
    }

    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }
}
