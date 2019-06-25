package com.varga.parcial.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @NotEmpty(message = "El comentario debe tener descripcion")
    @NotBlank(message = "El usuario debe tener descripcion")
    private String descripcion;
    private String fecha;
    private String owner;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    @JsonIgnore
    private Publicacion publicacion;

    @PrePersist
    public void createFecha(){
        if(Objects.isNull(this.getFecha())){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            this.fecha = formatter.format(LocalDate.now());
        }
    }

}
