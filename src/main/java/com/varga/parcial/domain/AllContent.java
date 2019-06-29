package com.varga.parcial.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllContent {
    private List<Publicacion> publicaciones;
    private List<Comentario> comentarios;
    private List<Usuario> usuarios;
}
