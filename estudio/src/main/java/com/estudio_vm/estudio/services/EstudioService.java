package com.estudio_vm.estudio.services;
import com.estudio_vm.estudio.models.EstudioModel;
import com.estudio_vm.estudio.repositories.EstudioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstudioService {
    private final EstudioRepository estudioRepository;
    public EstudioService(EstudioRepository estudioRepository) {
        this.estudioRepository = estudioRepository;
    }

    public List<EstudioModel> obtenerTodos(){
        return estudioRepository.findAll();
    }

    public Optional<EstudioModel> obtenerPorId(Long id) {
        return estudioRepository.findById(id);
    }

    public Optional<EstudioModel> obtenerPorNombre(String nombre) {
        return estudioRepository.findByNombre(nombre);
    }

    public EstudioModel guardar(EstudioModel estudio) {
        return estudioRepository.save(estudio);
    }

    public void eliminar(Long id) {
        estudioRepository.deleteById(id);
    }
}
