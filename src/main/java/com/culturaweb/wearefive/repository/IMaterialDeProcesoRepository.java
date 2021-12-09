package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.MaterialDeProceso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaterialDeProcesoRepository extends JpaRepository<MaterialDeProceso,Integer> {
    long removeByProceso_IdEquals(int id);
}
