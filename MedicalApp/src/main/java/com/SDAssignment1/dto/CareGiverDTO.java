package com.SDAssignment1.dto;

import com.SDAssignment1.entities.utils.Gender;
import com.SDAssignment1.entities.utils.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CareGiverDTO extends PersonDTO{

    @Builder
    public CareGiverDTO(String username, String password, UserRole userRole, String name, Date birthDate, Gender gender, String address) {
        super(username, password, userRole, name, birthDate, gender, address);
    }
}