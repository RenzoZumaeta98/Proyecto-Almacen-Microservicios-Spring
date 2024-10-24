
package com.proyecto.Proyecto.PApiUsuario.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreUsu;
    private String apellidoPatUsu;
    private String apellidoMatUsu;
    private String numeroDocUsu;
    private String telefonoUsu;
    private String emailUsu;
    private String codigoUsu;
    
    
}
