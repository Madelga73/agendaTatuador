package com.ayudante_vm.ayudante.services;
import com.ayudante_vm.ayudante.models.AyudanteModel;
import com.ayudante_vm.ayudante.repositories.AyudanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AyudanteService {
    private final AyudanteRepository ayudanteRepository;
    public AyudanteService(AyudanteRepository ayudanteRepository) {
        this.ayudanteRepository = ayudanteRepository;
    }

    public List<AyudanteModel> obtenerTodos(){
        return ayudanteRepository.findAll();
    }

    public Optional<AyudanteModel> obtenerPorId(Long id) {
        return ayudanteRepository.findById(id);
    }

    public Optional<AyudanteModel> obtenerPorRut(String rut) {
        return ayudanteRepository.findByRut(rut);
    }

    public AyudanteModel guardar(AyudanteModel ayudante) {
        return ayudanteRepository.save(ayudante);
    }

    public void eliminar(Long id) {
        ayudanteRepository.deleteById(id);
    }
}
