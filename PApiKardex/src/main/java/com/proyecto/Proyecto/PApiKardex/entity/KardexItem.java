
package com.proyecto.Proyecto.PApiKardex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class KardexItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kardexItemid;
    private Long cantidad;
    private String productoSK;
    private String estadoItem; // PENDIENTE, NO ENCONTRADO, ENCONTRADO
    @ManyToOne
    @JoinColumn(name = "kardexId") //Para que se genere la foreign key
    @JsonIgnore
    private Kardex kardex;
    
}
