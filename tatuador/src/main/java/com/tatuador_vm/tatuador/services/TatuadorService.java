package com.tatuador_vm.tatuador.services;
import com.tatuador_vm.tatuador.models.TatuadorModel;
import com.tatuador_vm.tatuador.repositories.TatuadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TatuadorService {
    private final TatuadorRepository tatuadorRepository;

    public TatuadorService(TatuadorRepository tatuadorRepository) {
        this.tatuadorRepository = tatuadorRepository;
    }

    public List<TatuadorModel> obtenerTodos(){
        return tatuadorRepository.findAll();
    }

    public Optional<TatuadorModel> obtenerPorId(Long id) {
        return tatuadorRepository.findById(id);
    }

    public Optional<TatuadorModel> obtenerPorRut(String rut) {
        return tatuadorRepository.findByRut(rut);
    }

    public TatuadorModel guardar(TatuadorModel tatuador) {
        return tatuadorRepository.save(tatuador);
    }

    public void eliminar(Long id) {
        tatuadorRepository.deleteById(id);
    }
}
