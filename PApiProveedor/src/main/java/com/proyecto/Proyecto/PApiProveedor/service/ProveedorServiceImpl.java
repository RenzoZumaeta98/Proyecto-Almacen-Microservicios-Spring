package com.proyecto.Proyecto.PApiProveedor.service;

import com.proyecto.Proyecto.PApiProveedor.dao.ProveedorRepository;
import com.proyecto.Proyecto.PApiProveedor.entity.Proveedor;
import com.proyecto.Proyecto.PApiProveedor.exception.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServiceImpl implements ProveedorService{
    
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> findAll() {
        return (List<Proveedor>)proveedorRepository.findAll();
    }

    @Override
    public Page<Proveedor> findAll(int page, int size) {
        return proveedorRepository.findAll( PageRequest.of(page, size) );
    }

    @Override
    public Proveedor findById(Long id) {
        return proveedorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Proveedor con id = " + id.toString() + " no encontrado"));
    }

    @Override
    public Proveedor add(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor update(Proveedor proveedor) {
        var proveedorDB = proveedorRepository.findById(proveedor.getId()).get();
        
        proveedorDB.setNombreProv(proveedor.getNombreProv());
        proveedorDB.setCodigoProv(proveedor.getCodigoProv());
        proveedorDB.setTelefonoProv(proveedor.getTelefonoProv());
        
        return proveedorRepository.save(proveedorDB);    
    }

    @Override
    public void delete(Long id) {
        var proveedorDB = proveedorRepository.findById(id).get();
        proveedorRepository.delete(proveedorDB);    
    }

    @Override
    public Proveedor findByCodigoProv(String codigoProv) {
        return proveedorRepository.findByCodigoProv(codigoProv).orElseThrow(()-> new EntityNotFoundException("Proveedor con código = " + codigoProv.toString() + " not found"));
    }
    
}
