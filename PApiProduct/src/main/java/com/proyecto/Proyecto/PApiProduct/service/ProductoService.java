package com.proyecto.Proyecto.PApiProduct.service;

import com.proyecto.Proyecto.PApiProduct.dto.ProductoDto;
import com.proyecto.Proyecto.PApiProduct.entity.Producto;
import java.util.List;
import org.springframework.data.domain.Page;


public interface ProductoService {
    public List<Producto> findAll();
    public Page<Producto>findAll(int page, int size);
    public ProductoDto findById(Long id);
    public ProductoDto findByNombreProd(String nombreProd);
    public ProductoDto findByProductoSK(String productoSK);
    public Producto add(Producto product);
    public Producto update(Producto product);
    public void delete(Long id);
}
