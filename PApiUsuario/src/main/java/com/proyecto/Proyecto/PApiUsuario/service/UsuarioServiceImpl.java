
package com.proyecto.Proyecto.PApiUsuario.service;

import com.proyecto.Proyecto.PApiUsuario.dao.UsuarioRepository;
import com.proyecto.Proyecto.PApiUsuario.entity.Usuario;
import com.proyecto.Proyecto.PApiUsuario.exception.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>)usuarioRepository.findAll();
    }

    @Override
    public Page<Usuario> findAll(int page, int size) {
        return usuarioRepository.findAll( PageRequest.of(page, size) );
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Usuario con id = " + id.toString() + " not found"));
    }

    @Override
    public Usuario add(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        var usuarioDB = usuarioRepository.findById(usuario.getId()).get();
        
        usuarioDB.setNombreUsu(usuario.getNombreUsu());
        usuarioDB.setApellidoPatUsu(usuario.getApellidoPatUsu());
        usuarioDB.setApellidoMatUsu(usuario.getApellidoMatUsu());
        usuarioDB.setNumeroDocUsu(usuario.getNumeroDocUsu());
        usuarioDB.setTelefonoUsu(usuario.getTelefonoUsu());
        usuarioDB.setEmailUsu(usuario.getEmailUsu());
        usuarioDB.setCodigoUsu(usuario.getCodigoUsu());
        
        return usuarioRepository.save(usuarioDB);
    }

    @Override
    public void delete(Long id) {
        var usuarioDB = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuarioDB);
    }

    @Override
    public Usuario findByCodigoUsu(String codigoUsu) {
        return usuarioRepository.findByCodigoUsu(codigoUsu).orElseThrow(()-> new EntityNotFoundException("Usuario con código = " + codigoUsu.toString() + " not found"));
    }
    
}
