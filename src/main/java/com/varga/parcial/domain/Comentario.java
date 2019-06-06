package com.varga.parcial.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private Integer id;
    private String descripcion;
    private LocalDateTime fecha;
    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "usuario_id", referencedColumnName = "id")
    //@JsonIgnore
    private String owner;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    @JsonIgnore
    private Publicacion publicacion;

    @PrePersist
    public void createFecha(){
        if(Objects.isNull(this.getFecha())){
            this.fecha = LocalDateTime.now();
        }
    }

}
