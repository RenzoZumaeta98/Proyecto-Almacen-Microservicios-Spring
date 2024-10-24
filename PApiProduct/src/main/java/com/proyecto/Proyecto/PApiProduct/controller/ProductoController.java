
package com.proyecto.Proyecto.PApiProduct.controller;

import com.proyecto.Proyecto.PApiProduct.dto.ProductoDto;
import com.proyecto.Proyecto.PApiProduct.entity.Producto;
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
import com.proyecto.Proyecto.PApiProduct.service.ProductoService;


@RestController
@RequestMapping("/api/v1/product")
public class ProductoController {
    
    @Autowired
    private ProductoService productService;
    
    @GetMapping("/findAll")
    public ResponseEntity<List<Producto>>  findAll(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK); 
    }
    
    @GetMapping("/findAll/page/{page}/size/{size}")
    public ResponseEntity<Page<Producto>> findAll(@PathVariable int page, @PathVariable int size){
        return new ResponseEntity<>(productService.findAll(page, size),HttpStatus.OK);
    } 
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductoDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(productService.findById(id),
                HttpStatus.OK);
    }
    @GetMapping("/findByNombreProd/{nombreProd}")
    public ResponseEntity<ProductoDto> findByNombreProd(@PathVariable String nombreProd){
        return new ResponseEntity<>(productService.findByNombreProd(nombreProd),
                HttpStatus.OK);
    }
    
    @GetMapping("/findByProductoSK/{productoSK}")
    public ResponseEntity<ProductoDto> findByProductoSK(@PathVariable String productoSK){
        return new ResponseEntity<>(productService.findByProductoSK(productoSK),
                HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Producto> add(@RequestBody Producto product){
        return new ResponseEntity<>(productService.add(product),
                HttpStatus.OK) ;
    }
    @PutMapping("/update")
    public ResponseEntity<Producto> update(@RequestBody Producto product){
        return new ResponseEntity<>(productService.update(product),
                HttpStatus.OK) ;
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
}
