package com.varga.parcial.repository;

import com.varga.parcial.domain.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
}
