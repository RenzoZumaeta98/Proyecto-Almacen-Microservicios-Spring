
package com.proyecto.Proyecto.PApiKardex.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Kardex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kardexId;
    private String codigoUsu;
    private String codigoAlm;
    private Date kardexDate;
    private String estadoPedido; //Puede ser ORDEN RECIBIDA, COMPLETO, INCOMPLETO
    private String estadoRetiro; //Puede ser ORDEN RECIBIDA, POR RETIRAR, RETIRADA
    @OneToMany(mappedBy = "kardex", cascade = CascadeType.ALL)
    private Set<KardexItem> kardexItems; 
    
    
    //Se crea un metodo para guardar el kardexId en cada kardexItem
    public void addKardexItem(KardexItem kardexItem){
        if(kardexItems==null){                       
            kardexItems = new HashSet<>();   //Si es nulo, se instancia
  
        }
        
        kardexItems.add(kardexItem);
        kardexItem.setKardex(this);
    }
}
