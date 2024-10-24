
package com.proyecto.Proyecto.PApiKardex.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Almacen {
    private Long id;
    private String nombreAlm;
    private String codigoAlm;
    private String ubicacionAlm;
    
}
