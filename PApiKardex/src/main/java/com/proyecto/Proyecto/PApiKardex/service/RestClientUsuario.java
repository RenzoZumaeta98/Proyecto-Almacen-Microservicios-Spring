
package com.proyecto.Proyecto.PApiKardex.service;

import com.proyecto.Proyecto.PApiKardex.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "usuario-service", url = "http://localhost:8080/")
public interface RestClientUsuario {
    @GetMapping("api/v1/usuario//findByCodigoUsu/{codigoUsu}")
    Usuario findByCodigoUsu(@PathVariable String codigoUsu);
}
