
package com.proyecto.Proyecto.PApiAlmacen.controller;

import com.proyecto.Proyecto.PApiAlmacen.entity.Almacen;
import com.proyecto.Proyecto.PApiAlmacen.service.AlmacenService;
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
@RequestMapping("api/v1/almacen")
public class AlmacenController {
    
    @Autowired
    private AlmacenService almacenService;
    
    
    @GetMapping("/findAll")
    public  ResponseEntity<List<Almacen>> findAll(){
        return new ResponseEntity<>( almacenService.findAll(), HttpStatus.OK); 
    }
    
    @GetMapping("/findAll/page/{page}/size/{size}")
    public ResponseEntity<Page<Almacen>> findAll(@PathVariable int page,@PathVariable int size){
        return new ResponseEntity<>(almacenService.findAll(page, size), HttpStatus.OK);
    }
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<Almacen> findById(@PathVariable Long id){
        return new ResponseEntity<>(almacenService.findById(id),HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Almacen> add(@RequestBody Almacen almacen){
        return new ResponseEntity<>(almacenService.add(almacen), HttpStatus.CREATED);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Almacen> update(@RequestBody Almacen almacen){
        return new ResponseEntity<>(almacenService.update(almacen), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
       almacenService.delete(id);
    }
    
    @GetMapping("/findByCodigoAlm/{codigoAlm}")
    public ResponseEntity<Almacen> findByCodigoAlm(@PathVariable String codigoAlm){
        return new ResponseEntity<>(almacenService.findByCodigoAlm(codigoAlm),HttpStatus.OK);
    }
    
}
