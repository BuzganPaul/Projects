package com.SDAssignment1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDTO {

    @JsonProperty("name")
    @NonNull
    @Size(min = 5, max = 30)
    private String name;

    @JsonProperty("dosage")
    @NonNull
    @Size(min = 5, max = 30)
    private String dosage;

}