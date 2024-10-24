
package com.proyecto.Proyecto.PApiProduct.dto;

import com.proyecto.Proyecto.PApiProduct.entity.Category;
import com.proyecto.Proyecto.PApiProduct.entity.Proveedor;
import lombok.Data;

@Data
public class ProductoDto {
    private Long id;
    private String nombreProd;
    private String productoSK;
    private String unidadMedida;
    private Category category;
    private Proveedor proveedor;

}
