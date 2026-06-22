package com.ayudante_vm.ayudante.repositories;
import com.ayudante_vm.ayudante.models.AyudanteModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AyudanteRepository extends JpaRepository<AyudanteModel, Long> {
    Optional<AyudanteModel> findByRut(String rut);
    boolean existsByRut(String rut);
}
