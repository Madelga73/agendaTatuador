package com.vendedor_vm.vendedor.services;
import com.vendedor_vm.vendedor.models.VendedorModel;
import com.vendedor_vm.vendedor.repositories.VendedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VendedorService {
    private final VendedorRepository vendedorRepository;
    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public List<VendedorModel> obtenerTodos(){
        return vendedorRepository.findAll();
    }

    public Optional<VendedorModel> obtenerPorId(Long id) {
        return vendedorRepository.findById(id);
    }

    public Optional<VendedorModel> obtenerPorNombre(String nombre) {
        return vendedorRepository.findByNombre(nombre);
    }

    public VendedorModel guardar(VendedorModel vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public void eliminar(Long id) {
        vendedorRepository.deleteById(id);
    }
}
