package com.SDAssignment1.dto;

import com.SDAssignment1.entities.utils.Gender;
import com.SDAssignment1.entities.utils.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO extends PersonDTO{

    @JsonProperty("specialization")
    @NonNull
    @Size(min = 3, max = 30)
    private String specialization;

    @Builder
    public DoctorDTO(String username, String password, UserRole userRole, String name, Date birthDate, Gender gender, String address, @NonNull String specialization) {
        super(username, password, userRole, name, birthDate, gender, address);
        this.specialization = specialization;
    }
}
