package com.proyecto.Proyecto.PApiProveedor.service;

import com.proyecto.Proyecto.PApiProveedor.entity.Proveedor;
import java.util.List;
import org.springframework.data.domain.Page;


public interface ProveedorService {
    public List<Proveedor> findAll();
    public Page<Proveedor> findAll(int page, int size);
    public Proveedor findById(Long id);
    public Proveedor add(Proveedor proveedor);
    public Proveedor update(Proveedor proveedor);
    public void delete (Long id);
    public Proveedor findByCodigoProv(String codigoProv);
}
