package com.SDAssignment1.entities;

import com.SDAssignment1.entities.utils.Gender;
import com.SDAssignment1.entities.utils.UserRole;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class CareGiver extends Person{

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = false)
    List<Patient> patientsList = new ArrayList<>();

    @Builder
    public CareGiver(UUID id, String username, String password, UserRole userRole, String name, Date birthDate, Gender gender, String address) {
        super(id, username, password, userRole, name, birthDate, gender, address);
    }

}
