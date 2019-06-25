package com.varga.parcial.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty(message = "El usuario debe tener nombre")
    @NotBlank(message = "El usuario debe tener nombre")
    private String nombre;
    @NotEmpty(message = "El usuario debe tener apellido")
    @NotBlank(message = "El usuario debe tener apellido")
    private String apellido;
    private String browser;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "usuario")
    @ToString.Exclude
    List<Publicacion> publicaciones;
}
