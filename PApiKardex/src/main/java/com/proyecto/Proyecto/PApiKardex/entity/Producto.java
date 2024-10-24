
package com.proyecto.Proyecto.PApiKardex.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Producto {
    private Long id;
    private String nombreProd;
    private String codigoCat;
    private String codigoProv;
    private String productoSK;
}
