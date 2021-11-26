package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.ModeloZapato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IModeloZapatoRepository extends JpaRepository<ModeloZapato, Integer> {
    boolean existsByNombreEquals(String nombre);
    List<ModeloZapato> findByNombreContains(String nombre);
}
