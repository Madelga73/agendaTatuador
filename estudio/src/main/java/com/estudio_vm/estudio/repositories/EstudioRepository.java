package com.estudio_vm.estudio.repositories;
import com.estudio_vm.estudio.models.EstudioModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudioRepository extends JpaRepository<EstudioModel, Long>{
    Optional<EstudioModel> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
