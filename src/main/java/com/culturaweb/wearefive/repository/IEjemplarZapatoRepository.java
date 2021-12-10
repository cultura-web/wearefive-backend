package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.EjemplarZapato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEjemplarZapatoRepository extends JpaRepository<EjemplarZapato,Integer> {
}
