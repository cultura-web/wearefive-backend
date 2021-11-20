package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.Admin;
import com.culturaweb.wearefive.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {
}
