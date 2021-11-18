package com.culturaweb.wearefive.repository;

import com.culturaweb.wearefive.model.QA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IQARepository extends JpaRepository<QA, Integer> {
    Optional<QA> findByIdEquals(Integer id);

}
