package com.proyecto.Proyecto.PApiCategory.dao;

import com.proyecto.Proyecto.PApiCategory.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Long>{
    Optional<Category> findByNombreCat (String nombreCat);
    Optional<Category> findByCodigoCat (String codigoCat);
}
