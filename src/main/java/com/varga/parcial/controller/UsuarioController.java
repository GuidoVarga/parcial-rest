package com.varga.parcial.controller;

import com.varga.parcial.domain.Publicacion;
import com.varga.parcial.domain.Usuario;
import com.varga.parcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepository uRepository;

    @GetMapping("")
    public List<Usuario> getUsuarios(){
        return uRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable("id") Integer id){
        Usuario usuario = uRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        return usuario;
    }

    @PostMapping("")
    public void addUsuario(@RequestHeader(value="User-Agent") String userAgent, @RequestBody Usuario usuario){
        usuario.setBrowser(userAgent);
        uRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public void updateUsuario(@RequestHeader(value="User-Agent") String userAgent, @PathVariable("id") Integer id, @RequestBody Usuario newUsuario){
        Usuario usuario = uRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        usuario.setNombre(newUsuario.getNombre());
        usuario.setApellido((newUsuario.getApellido()));
        usuario.setBrowser(userAgent);
        uRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable("id") Integer id){
        Usuario usuario = uRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        uRepository.delete(usuario);
    }
    
}
