
package com.proyecto.Proyecto.PApiAlmacen.dao;

import com.proyecto.Proyecto.PApiAlmacen.entity.Almacen;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlmacenRepository extends JpaRepository<Almacen, Long>{
    Optional<Almacen> findByCodigoAlm(String codigoAlm);
}
