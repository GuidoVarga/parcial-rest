package com.varga.parcial.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publicaciones")
public class Publicacion {
    @Id
    @GeneratedValue
    private Integer id;
    private String descripcion;
    private String foto;
    private String fechaPublicacion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "publicacion")
    @ToString.Exclude
    List<Comentario> comentarios;

    @PrePersist
    public void createFecha(){
        if(Objects.isNull(this.getFechaPublicacion())){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

            fechaPublicacion = formatter.format(LocalDate.now());
        }
    }

}
