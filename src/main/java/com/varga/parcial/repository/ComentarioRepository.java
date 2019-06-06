package com.varga.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.varga.parcial.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
