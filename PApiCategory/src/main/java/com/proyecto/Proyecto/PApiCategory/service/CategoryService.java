package com.proyecto.Proyecto.PApiCategory.service;

import com.proyecto.Proyecto.PApiCategory.entity.Category;
import java.util.List;
import org.springframework.data.domain.Page;


public interface CategoryService {
    public List<Category> findAll();
    public Page<Category> findAll(int page, int size);
    public Category findById(Long id);
    public Category add(Category category);
    public Category update(Category category);
    public void delete (Long id);
    public Category findByNombreCat(String nombreCat);
    public Category findByCodigoCat(String codigoCat);
    
}
