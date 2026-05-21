package com.tatuador_vm.tatuador.repositories;
import com.tatuador_vm.tatuador.models.TatuadorModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TatuadorRepository extends JpaRepository<TatuadorModel, Long> {
    Optional<TatuadorModel> findByRut(String rut);
    boolean existsByRut(String rut);
}
