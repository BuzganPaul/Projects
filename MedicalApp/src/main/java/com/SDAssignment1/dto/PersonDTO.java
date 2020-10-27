package com.SDAssignment1.dto;

//import com.fasterxml.jackson.annotation.JsonProperty;

import com.SDAssignment1.entities.utils.Gender;
import com.SDAssignment1.entities.utils.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonDTO {

    @JsonProperty("username")
    @NonNull
    @Size(min = 5, max = 30)
    private String username;

    @JsonProperty("password")
    @Size(min = 5, max = 30)
    private String password;

    @JsonProperty("userRole")
    @NonNull
    private UserRole userRole;

    @JsonProperty("name")
    @NonNull
    @Size(min = 5, max = 30)
    private String name;

    @JsonProperty("birthDate")
    @NonNull
    private Date birthDate;

    @JsonProperty("gender")
    @NonNull
    private Gender gender;

    @JsonProperty("address")
    @NonNull
    @Size(min = 5, max = 50)
    private String address;
}
