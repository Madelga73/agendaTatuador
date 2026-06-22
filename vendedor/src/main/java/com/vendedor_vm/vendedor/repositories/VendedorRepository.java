package com.vendedor_vm.vendedor.repositories;
import com.vendedor_vm.vendedor.models.VendedorModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<VendedorModel, Long> {
    Optional<VendedorModel> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
