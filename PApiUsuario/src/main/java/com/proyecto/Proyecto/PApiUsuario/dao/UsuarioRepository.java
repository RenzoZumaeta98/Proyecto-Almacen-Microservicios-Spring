
package com.proyecto.Proyecto.PApiUsuario.dao;

import com.proyecto.Proyecto.PApiUsuario.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByCodigoUsu(String codigoUsu);
}
