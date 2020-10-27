package com.SDAssignment1.repositories;

import com.SDAssignment1.entities.CareGiver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface CareGiverRepository extends JpaRepository<CareGiver, UUID> {

    CareGiver findByUsername(String username);

    @Query("SELECT c FROM CareGiver c ORDER BY c.name DESC")
    Page<CareGiver> findAllCareGivers(Pageable pageable);

    @Transactional
    void deleteByUsername(String username);
}
