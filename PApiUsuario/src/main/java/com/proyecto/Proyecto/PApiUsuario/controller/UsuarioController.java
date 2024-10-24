
package com.proyecto.Proyecto.PApiUsuario.controller;

import com.proyecto.Proyecto.PApiUsuario.entity.Usuario;
import com.proyecto.Proyecto.PApiUsuario.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    
    @GetMapping("/findAll")
    public  ResponseEntity<List<Usuario>> findAll(){
        return new ResponseEntity<>( usuarioService.findAll(), HttpStatus.OK); 
    }
    
    @GetMapping("/findAll/page/{page}/size/{size}")
    public ResponseEntity<Page<Usuario>> findAll(@PathVariable int page,@PathVariable int size){
        return new ResponseEntity<>(usuarioService.findAll(page, size), HttpStatus.OK);
    }
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return new ResponseEntity<>(usuarioService.findById(id),HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Usuario> add(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.add(usuario), HttpStatus.CREATED);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.update(usuario), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
       usuarioService.delete(id);
    }
    
    @GetMapping("/findByCodigoUsu/{codigoUsu}")
    public ResponseEntity<Usuario> findByCodigoUsu(@PathVariable String codigoUsu){
        return new ResponseEntity<>(usuarioService.findByCodigoUsu(codigoUsu),HttpStatus.OK);
    }
    
}
