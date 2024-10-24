package com.proyecto.Proyecto.PApiProveedor.dao;

import com.proyecto.Proyecto.PApiProveedor.entity.Proveedor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
    Optional<Proveedor> findByCodigoProv(String codigoProv);
}
