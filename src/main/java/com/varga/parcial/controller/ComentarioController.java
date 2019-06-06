package com.varga.parcial.controller;

import com.varga.parcial.domain.Comentario;
import com.varga.parcial.domain.Publicacion;
import com.varga.parcial.domain.Usuario;
import com.varga.parcial.repository.ComentarioRepository;
import com.varga.parcial.repository.PublicacionRepository;
import com.varga.parcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("comentarios")
public class ComentarioController {

    @Autowired
    UsuarioRepository uRepository;
    @Autowired
    PublicacionRepository pRepository;
    @Autowired
    ComentarioRepository cRepository;

    @PostMapping("/publicaciones/{idPublicacion}/usuarios/{idUsuario}")
    public void addComentario(@PathVariable("idPublicacion") Integer idPublicacion, @PathVariable("idUsuario") Integer idUsuario, @RequestBody Comentario comentario){
        Publicacion publicacion = pRepository.findById(idPublicacion).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        Usuario usuario = uRepository.findById(idUsuario).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        comentario.setOwner(usuario.getNombre());
        comentario.setPublicacion(publicacion);
        cRepository.save(comentario);
        publicacion.getComentarios().add(comentario);
        pRepository.save(publicacion);
    }

    @DeleteMapping("/{id}")
    public void deleteComentario(@PathVariable("id") Integer id){
        Comentario comentario = cRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        Publicacion publicacion = pRepository.findById(comentario.getId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        cRepository.delete(comentario);
        publicacion.getComentarios().removeIf(c -> c.getId().equals(comentario.getId()));
        pRepository.save(publicacion);
    }
}
