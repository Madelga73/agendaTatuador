package com.sesion_vm.sesion.Repositories;
import com.sesion_vm.sesion.models.SesionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesionRepository extends JpaRepository<SesionModel, Long>  {
    List<SesionModel> findByIdAyudante(Long idAyudante);
    List<SesionModel> findByIdCliente(Long idCliente);
    List<SesionModel> findByIdEstudio(Long idEstudio);
    List<SesionModel> findByIdPago(Long idPago);
    List<SesionModel> findByIdTatuador(Long idTatuador);
    List<SesionModel> findByIdTatuaje(Long idTatuaje);
}
