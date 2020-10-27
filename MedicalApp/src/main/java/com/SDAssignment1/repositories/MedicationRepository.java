package com.SDAssignment1.repositories;

import com.SDAssignment1.entities.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {

    Medication findByName(String name);

    @Query("SELECT m FROM Medication m ORDER BY m.name DESC")
    Page<Medication> findAllMedication(Pageable pageable);

    @Transactional
    void deleteByName(String name);
}
