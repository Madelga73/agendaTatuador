package com.tatuaje_vm.tatuaje.repositories;
import com.tatuaje_vm.tatuaje.models.TatuajeModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TatuajeRepository extends JpaRepository<TatuajeModel, Long> {
    Optional<TatuajeModel> findByDescripcion(String descripcion);
    boolean existsByDescripcion(String descripcion);
}
