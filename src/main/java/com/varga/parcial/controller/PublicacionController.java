package com.varga.parcial.controller;

import com.varga.parcial.domain.Publicacion;
import com.varga.parcial.domain.Usuario;
import com.varga.parcial.repository.PublicacionRepository;
import com.varga.parcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("publicaciones")
public class PublicacionController {

    @Autowired
    PublicacionRepository pRepository;
    @Autowired
    UsuarioRepository uRepository;

    @GetMapping("")
    public List<Publicacion> getPublicaciones(){
        return pRepository.findAll();
    }

    @GetMapping("/{id}")
    public Publicacion getPublicacion(@PathVariable("id") Integer id){
        Publicacion publicacion = pRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        return publicacion;
    }

    @PostMapping("/usuarios/{id}")
    public void addPublicacion(@PathVariable("id")Integer id, @RequestBody @NotNull Publicacion publicacion){
        Usuario usuario = uRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        usuario.getPublicaciones().add(publicacion);
        publicacion.setUsuario(usuario);
        pRepository.save(publicacion);
        uRepository.save(usuario);
    }
}
