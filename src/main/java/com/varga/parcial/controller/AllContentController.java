package com.varga.parcial.controller;

import com.varga.parcial.domain.AllContent;
import com.varga.parcial.domain.Comentario;
import com.varga.parcial.domain.Publicacion;
import com.varga.parcial.domain.Usuario;
import com.varga.parcial.services.AllContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("allContent")
public class AllContentController {

    @Autowired
    AllContentService allContentService;

    @GetMapping("")
    public AllContent getAllContent(){
        CompletableFuture<List<Publicacion>> publicaciones = allContentService.getAllPublicaciones();
        CompletableFuture<List<Comentario>> comentarios = allContentService.getAllComentarios();
        CompletableFuture<List<Usuario>> usuarios = allContentService.getAllUsuarios();
        return new AllContent(publicaciones.join(), comentarios.join(), usuarios.join());
    }
}
