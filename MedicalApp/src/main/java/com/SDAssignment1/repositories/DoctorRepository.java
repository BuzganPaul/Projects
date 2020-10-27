package com.SDAssignment1.repositories;

import com.SDAssignment1.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface DoctorRepository  extends JpaRepository<Doctor, UUID> {

    Doctor findByUsername(String username);

    @Query("SELECT d FROM Doctor d ORDER BY d.name DESC")
    Page<Doctor> findAllDoctors(Pageable pageable);

    @Transactional
    void deleteByUsername(String username);

}
