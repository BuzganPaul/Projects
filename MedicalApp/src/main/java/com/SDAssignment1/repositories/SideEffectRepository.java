package com.SDAssignment1.repositories;

import com.SDAssignment1.entities.SideEffect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface SideEffectRepository extends JpaRepository<SideEffect, UUID> {

    SideEffect findByName(String name);

    @Query("SELECT s FROM SideEffect s ORDER BY s.name DESC")
    Page<SideEffect> findAllSideEffect(Pageable pageable);

    @Transactional
    void deleteByName(String name);
}
