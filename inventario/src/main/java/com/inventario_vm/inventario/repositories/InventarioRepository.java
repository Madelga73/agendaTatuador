package com.inventario_vm.inventario.repositories;
import com.inventario_vm.inventario.models.InventarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioModel, Long> {
    List<InventarioModel> findByIdTatuador(Long idTatuador);
    List<InventarioModel> findByIdVendedor(Long idVendedor);
}
