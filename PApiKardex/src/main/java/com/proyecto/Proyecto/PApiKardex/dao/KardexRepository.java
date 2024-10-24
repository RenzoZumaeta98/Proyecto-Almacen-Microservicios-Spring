
package com.proyecto.Proyecto.PApiKardex.dao;

import com.proyecto.Proyecto.PApiKardex.entity.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KardexRepository extends JpaRepository<Kardex, Long>{
    public Kardex findByCodigoUsu(String codigoUsu);
}
