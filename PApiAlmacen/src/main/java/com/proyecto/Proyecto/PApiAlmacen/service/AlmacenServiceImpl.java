
package com.proyecto.Proyecto.PApiAlmacen.service;

import com.proyecto.Proyecto.PApiAlmacen.dao.AlmacenRepository;
import com.proyecto.Proyecto.PApiAlmacen.entity.Almacen;
import com.proyecto.Proyecto.PApiAlmacen.exception.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AlmacenServiceImpl implements AlmacenService{
    
    @Autowired
    private AlmacenRepository almacenRepository;

    @Override
    public List<Almacen> findAll() {
        return (List<Almacen>)almacenRepository.findAll();
    }

    @Override
    public Page<Almacen> findAll(int page, int size) {
                return almacenRepository.findAll( PageRequest.of(page, size) );
    }

    @Override
    public Almacen findById(Long id) {
        return almacenRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Almacen con id = " + id.toString() + " not found"));
    }

    @Override
    public Almacen add(Almacen almacen) {
        return almacenRepository.save(almacen);
    }

    @Override
    public Almacen update(Almacen almacen) {
        var almacenDB = almacenRepository.findById(almacen.getId()).get();
        
        almacenDB.setNombreAlm(almacen.getNombreAlm());
        almacenDB.setCodigoAlm(almacen.getCodigoAlm());
        almacenDB.setUbicacionAlm(almacen.getUbicacionAlm());
        
        return almacenRepository.save(almacenDB);
    }

    @Override
    public void delete(Long id) {
        var almacenDB = almacenRepository.findById(id).get();
        almacenRepository.delete(almacenDB);    }

    @Override
    public Almacen findByCodigoAlm(String codigoAlm) {
        return almacenRepository.findByCodigoAlm(codigoAlm).orElseThrow(()-> new EntityNotFoundException("Almacen con código = " + codigoAlm.toString() + " not found"));
    }
    
}
