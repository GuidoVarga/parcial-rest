package com.varga.parcial.services;

import com.varga.parcial.domain.Comentario;
import com.varga.parcial.domain.Publicacion;
import com.varga.parcial.domain.Usuario;
import com.varga.parcial.repository.ComentarioRepository;
import com.varga.parcial.repository.PublicacionRepository;
import com.varga.parcial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import static java.lang.Thread.sleep;

@Service
public class AllContentService {

    @Autowired
    ComentarioRepository cRepository;
    @Autowired
    PublicacionRepository pRepository;
    @Autowired
    UsuarioRepository uRepository;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Publicacion>> getAllPublicaciones(){
        try {
            sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(pRepository.findAll());
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Comentario>> getAllComentarios(){
        try {
            sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(cRepository.findAll());
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Usuario>> getAllUsuarios(){
        try {
            sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(uRepository.findAll());
    }
}
