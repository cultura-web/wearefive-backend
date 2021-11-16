package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.domain.QA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQARepository extends JpaRepository<QA, Integer> {
}
