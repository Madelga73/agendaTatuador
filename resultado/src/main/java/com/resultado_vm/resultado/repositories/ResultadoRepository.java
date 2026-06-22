package com.resultado_vm.resultado.repositories;
import com.resultado_vm.resultado.models.ResultadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoModel, Long> {
    List<ResultadoModel> findByIdCliente(Long idCliente);
    List<ResultadoModel> findByIdTatuaje(Long idTatuaje);
}
