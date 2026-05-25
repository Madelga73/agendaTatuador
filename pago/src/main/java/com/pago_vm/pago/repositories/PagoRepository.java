package com.pago_vm.pago.repositories;
import com.pago_vm.pago.models.PagoModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<PagoModel, Long> {
    Optional<PagoModel> findByNumBoleta(String numBoleta);
    boolean existsByNumBoleta(String numBoleta);
}
