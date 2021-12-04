package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProcesoRepository extends JpaRepository<Proceso,Integer> {
}
