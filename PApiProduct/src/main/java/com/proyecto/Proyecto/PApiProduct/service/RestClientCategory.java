package com.proyecto.Proyecto.PApiProduct.service;

import com.proyecto.Proyecto.PApiProduct.entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "category-service", url = "http://localhost:8080/")
public interface RestClientCategory {
    @GetMapping("api/v1/category//findByCodigoCat/{codigoCat}")
    Category findByCodigoCat(@PathVariable String codigoCat);
}
