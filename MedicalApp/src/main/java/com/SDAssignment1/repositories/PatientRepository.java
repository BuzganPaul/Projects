package com.SDAssignment1.repositories;

import com.SDAssignment1.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    Patient findByUsername(String username);

    @Query("SELECT p FROM Patient p ORDER BY p.name DESC")
    Page<Patient> findAllPatients(Pageable pageable);

    @Transactional
    void deleteByUsername(String username);
}
