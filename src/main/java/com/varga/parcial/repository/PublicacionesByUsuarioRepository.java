package com.varga.parcial.repository;

import com.varga.parcial.domain.PublicacionesByUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PublicacionesByUsuarioRepository extends JpaRepository<PublicacionesByUsuario, String> {
    String NATIVE_QUERY = "Select u.nombre, count(p.id) as cantidad from usuario u inner join publicaciones as p on u.id = usuario_id group by u.nombre";

    @Query(value = NATIVE_QUERY, nativeQuery = true)
    List<PublicacionesByUsuario> getPublicacionesPorUsuario();
}
