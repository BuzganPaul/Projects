package com.SDAssignment1.entities;


import com.SDAssignment1.entities.utils.Gender;
import com.SDAssignment1.entities.utils.UserRole;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Patient extends Person{

    @Size(min = 5, max = 30)
    @Column(nullable = false)
    private String medicalRecord;

    @Builder
    public Patient(UUID id, String username, String password, UserRole userRole, String name, Date birthDate, Gender gender, String address, String medicalRecord) {
        super(id, username, password, userRole, name, birthDate, gender, address);
        this.medicalRecord = medicalRecord;
    }

}
