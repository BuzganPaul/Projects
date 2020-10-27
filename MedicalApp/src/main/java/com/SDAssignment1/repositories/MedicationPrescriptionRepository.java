package com.SDAssignment1.repositories;

import com.SDAssignment1.entities.Medication;
import com.SDAssignment1.entities.MedicationPrescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface MedicationPrescriptionRepository extends JpaRepository<MedicationPrescription, UUID> {

    MedicationPrescription findByMedication(Medication medication);

    @Query("SELECT m FROM MedicationPrescription m ORDER BY m.medication.name DESC")
    Page<MedicationPrescription> findAllMedicationPrescription(Pageable pageable);

    @Transactional
    void deleteByMedication(Medication medication);

}
