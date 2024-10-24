package com.proyecto.Proyecto.PApiProduct.service;

import com.proyecto.Proyecto.PApiProduct.entity.Proveedor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "proveedor-service", url = "http://localhost:8080/")
public interface RestClientProveedor {
    @GetMapping("api/v1/proveedor//findByCodigoProv/{codigoProv}")
    Proveedor findByCodigoProv(@PathVariable String codigoProv);
}
