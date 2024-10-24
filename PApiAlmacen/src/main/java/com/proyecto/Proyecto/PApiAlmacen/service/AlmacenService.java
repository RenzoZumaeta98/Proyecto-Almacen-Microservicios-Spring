
package com.proyecto.Proyecto.PApiAlmacen.service;

import com.proyecto.Proyecto.PApiAlmacen.entity.Almacen;
import java.util.List;
import org.springframework.data.domain.Page;


public interface AlmacenService {
    public List<Almacen> findAll();
    public Page<Almacen> findAll(int page, int size);
    public Almacen findById(Long id);
    public Almacen add(Almacen almacen);
    public Almacen update(Almacen almacen);
    public void delete (Long id);
    public Almacen findByCodigoAlm(String codigoAlm);
}
