package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {
}
