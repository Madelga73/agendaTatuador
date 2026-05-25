package com.tatuaje_vm.tatuaje.services;
import com.tatuaje_vm.tatuaje.models.TatuajeModel;
import com.tatuaje_vm.tatuaje.repositories.TatuajeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TatuajeService {
    private final TatuajeRepository tatuajeRepository;

    public TatuajeService(TatuajeRepository tatuajeRepository) {
        this.tatuajeRepository = tatuajeRepository;
    }

    public List<TatuajeModel> obtenerTodos(){
        return tatuajeRepository.findAll();
    }

    public Optional<TatuajeModel> obtenerPorId(Long id) {
        return tatuajeRepository.findById(id);
    }

    public Optional<TatuajeModel> obtenerPorDescripcion(String descripcion) {
        return tatuajeRepository.findByDescripcion(descripcion);
    }

    public TatuajeModel guardar(TatuajeModel tatuaje) {
        return tatuajeRepository.save(tatuaje);
    }

    public void eliminar(Long id) {
        tatuajeRepository.deleteById(id);
    }
}