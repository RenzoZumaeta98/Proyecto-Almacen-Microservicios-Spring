package com.proyecto.Proyecto.PApiProveedor.controller;

import com.proyecto.Proyecto.PApiProveedor.entity.Proveedor;
import com.proyecto.Proyecto.PApiProveedor.service.ProveedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/proveedor")
public class ProveedorController {
    
    @Autowired
    private ProveedorService proveedorService;
    
    
    @GetMapping("/findAll")
    public  ResponseEntity<List<Proveedor>> findAll(){
        return new ResponseEntity<>( proveedorService.findAll(), HttpStatus.OK); 
    }
    
    @GetMapping("/findAll/page/{page}/size/{size}")
    public ResponseEntity<Page<Proveedor>> findAll(@PathVariable int page,@PathVariable int size){
        return new ResponseEntity<>(proveedorService.findAll(page, size), HttpStatus.OK);
    }
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<Proveedor> findById(@PathVariable Long id){
        return new ResponseEntity<>(proveedorService.findById(id),HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Proveedor> add(@RequestBody Proveedor proveedor){
        return new ResponseEntity<>(proveedorService.add(proveedor), HttpStatus.CREATED);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Proveedor> update(@RequestBody Proveedor proveedor){
        return new ResponseEntity<>(proveedorService.update(proveedor), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
       proveedorService.delete(id);
    }
    
    @GetMapping("/findByCodigoProv/{codigoProv}")
    public ResponseEntity<Proveedor> findByCodigoProv(@PathVariable String codigoProv){
        return new ResponseEntity<>(proveedorService.findByCodigoProv(codigoProv),HttpStatus.OK);
    }
    
}
