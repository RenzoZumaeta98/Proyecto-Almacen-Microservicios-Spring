package com.proyecto.Proyecto.PApiKardex.service;

import com.proyecto.Proyecto.PApiKardex.entity.Almacen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "almacen-service", url = "http://localhost:8080/")
public interface RestClientAlmacen {
    @GetMapping("api/v1/almacen//findByCodigoAlm/{codigoAlm}")
    Almacen findByCodigoAlm(@PathVariable String codigoAlm);
}
