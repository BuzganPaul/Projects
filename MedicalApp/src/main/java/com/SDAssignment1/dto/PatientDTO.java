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
public class PatientDTO extends PersonDTO {

    @JsonProperty("medicalRecord")
    @NonNull
    @Size(min = 5, max = 50)
    private String medicalRecord;

    @Builder
    public PatientDTO(String username, String password, UserRole userRole, String name, Date birthDate, Gender gender, String address, @NonNull String medicalRecord) {
        super(username, password, userRole, name, birthDate, gender, address);
        this.medicalRecord = medicalRecord;
    }
}