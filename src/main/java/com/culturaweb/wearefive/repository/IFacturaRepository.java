package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, String> {
}
