
package com.proyecto.Proyecto.PApiUsuario.service;

import com.proyecto.Proyecto.PApiUsuario.entity.Usuario;
import java.util.List;
import org.springframework.data.domain.Page;


public interface UsuarioService {
    public List<Usuario> findAll();
    public Page<Usuario> findAll(int page, int size);
    public Usuario findById(Long id);
    public Usuario add(Usuario usuario);
    public Usuario update(Usuario usuario);
    public void delete (Long id);
    public Usuario findByCodigoUsu(String codigoUsu);
}
