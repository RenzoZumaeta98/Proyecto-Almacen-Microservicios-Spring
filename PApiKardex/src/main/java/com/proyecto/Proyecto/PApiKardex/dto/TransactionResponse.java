
package com.proyecto.Proyecto.PApiKardex.dto;

import com.proyecto.Proyecto.PApiKardex.entity.Almacen;
import com.proyecto.Proyecto.PApiKardex.entity.Kardex;
import com.proyecto.Proyecto.PApiKardex.entity.Producto;
import com.proyecto.Proyecto.PApiKardex.entity.Usuario;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionResponse {
    public Kardex kardex;
    public Usuario usuario;
    public Almacen almacen;
    public List<Producto> productos;
    
    
}
