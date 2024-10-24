package com.proyecto.Proyecto.PApiProduct.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreProd;
    private String codigoCat;
    private String codigoProv;
    private String productoSK;
    private String unidadMedida;
}
