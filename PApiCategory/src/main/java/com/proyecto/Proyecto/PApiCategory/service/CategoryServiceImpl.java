
package com.proyecto.Proyecto.PApiCategory.service;

import com.proyecto.Proyecto.PApiCategory.dao.CategoryRepository;
import com.proyecto.Proyecto.PApiCategory.entity.Category;
import com.proyecto.Proyecto.PApiCategory.exception.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return (List<Category>)categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(int page, int size) {
        return categoryRepository.findAll( PageRequest.of(page, size) );
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("CATEGORIA con id = " + id.toString() + " not found"));
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        var categoryDB = categoryRepository.findById(category.getId()).get();
        
        categoryDB.setNombreCat(category.getNombreCat());
        categoryDB.setCodigoCat(category.getCodigoCat());
        
        return categoryRepository.save(categoryDB);    }

    @Override
    public void delete(Long id) {
        var categoryDB = categoryRepository.findById(id).get();
        categoryRepository.delete(categoryDB);  
    }

    @Override
    public Category findByNombreCat(String nombreCat) {
        return categoryRepository.findByNombreCat(nombreCat).orElseThrow(()-> new EntityNotFoundException("Categoria con nombre = " + nombreCat.toString() + " not found"));

    }

    @Override
    public Category findByCodigoCat(String codigoCat) {
        return categoryRepository.findByCodigoCat(codigoCat).orElseThrow(()-> new EntityNotFoundException("Categoria con código = " + codigoCat.toString() + " not found"));
    }
    
}
