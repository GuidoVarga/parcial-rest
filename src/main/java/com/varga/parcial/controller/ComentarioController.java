package com.varga.parcial.controller;

import com.varga.parcial.domain.Comentario;
import com.varga.parcial.domain.Publicacion;
import com.varga.parcial.domain.Usuario;
import com.varga.parcial.repository.ComentarioRepository;
import com.varga.parcial.repository.PublicacionRepository;
import com.varga.parcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("comentarios")
@PropertySource("classpath:resources/application.properties")
public class ComentarioController {

    @Value("${expirationTime}")
    private static int EXPIRATION_TIME;

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

    @Scheduled(fixedDelay = 2000)
    public void deleteOldComments(){
        List<Comentario> comentarios = cRepository.findAll();
        comentarios.stream().forEach(c -> {
            Calendar currentDate = Calendar.getInstance();
            Calendar commentDate = Calendar.getInstance();
            currentDate.setTime(new Date());
            commentDate.setTime(new Date(c.getFecha()));
            if(currentDate.get(Calendar.YEAR) > commentDate.get(Calendar.YEAR)){
                cRepository.delete(c);
            }
        });
    }
}
