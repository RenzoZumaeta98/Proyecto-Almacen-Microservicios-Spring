package com.proyecto.Proyecto.PApiProduct.dao;

import com.proyecto.Proyecto.PApiProduct.dto.ProductoDto;
import com.proyecto.Proyecto.PApiProduct.entity.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Long>{
    Optional<ProductoDto> findById(int id);
    Producto findByNombreProd(String nombreProd);
    Producto findByProductoSK(String productoSk);
}
