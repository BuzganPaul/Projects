package com.SDAssignment1.repositories;

import com.SDAssignment1.entities.MedicationPlan;
import com.SDAssignment1.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, UUID> {

    MedicationPlan findByPatient(Patient patient);

    @Query("SELECT m FROM MedicationPlan m ORDER BY m.patient.name DESC")
    Page<MedicationPlan> findAllMedicationPlan(Pageable pageable);

    @Transactional
    void deleteByPatient(Patient patient);
}
