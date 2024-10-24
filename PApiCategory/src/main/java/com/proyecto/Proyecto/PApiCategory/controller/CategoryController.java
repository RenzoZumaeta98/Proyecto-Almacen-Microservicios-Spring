package com.proyecto.Proyecto.PApiCategory.controller;

import com.proyecto.Proyecto.PApiCategory.entity.Category;
import com.proyecto.Proyecto.PApiCategory.service.CategoryService;
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
@RequestMapping("api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    
    @GetMapping("/findAll")
    public  ResponseEntity<List<Category>> findAll(){
        return new ResponseEntity<>( categoryService.findAll(), HttpStatus.OK); 
    }
    
    @GetMapping("/findAll/page/{page}/size/{size}")
    public ResponseEntity<Page<Category>> findAll(@PathVariable int page,@PathVariable int size){
        return new ResponseEntity<>(categoryService.findAll(page, size), HttpStatus.OK);
    }
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.findById(id),HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.add(category), HttpStatus.CREATED);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
       categoryService.delete(id);
    }
    
    @GetMapping("/findByNombreCat/{nombreCat}")
    public ResponseEntity<Category> findByNombreCat(@PathVariable String nombreCat){
        return new ResponseEntity<>(categoryService.findByNombreCat(nombreCat),HttpStatus.OK);
    }
    
    @GetMapping("/findByCodigoCat/{codigoCat}")
    public ResponseEntity<Category> findByCodigoCat(@PathVariable String codigoCat){
        return new ResponseEntity<>(categoryService.findByCodigoCat(codigoCat),HttpStatus.OK);
    }
    
    
}
