package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface IMaterialRepository extends JpaRepository<Material,Integer> {
}
