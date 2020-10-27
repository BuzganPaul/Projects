package com.SDAssignment1.services.utils.convertors;

import com.SDAssignment1.dto.PatientDTO;
import com.SDAssignment1.entities.Patient;

public class PatientConvertor {
    public static PatientDTO entityToDTOConversion(Patient patient)
    {
        PatientDTO patientDTO =  PatientDTO.builder()
                .username(patient.getUsername())
                .password(null)
                .userRole(patient.getUserRole())
                .name(patient.getName())
                .birthDate(patient.getBirthDate())
                .address(patient.getAddress())
                .gender(patient.getGender())
                .medicalRecord(patient.getMedicalRecord())
                .build();

        return patientDTO;
    }

    public static Patient dTOToEntityConversion(PatientDTO patientDTO)
    {
        Patient patient = Patient.builder()
                .username(patientDTO.getUsername())
                .password(patientDTO.getPassword())
                .userRole(patientDTO.getUserRole())
                .name(patientDTO.getName())
                .birthDate(patientDTO.getBirthDate())
                .address(patientDTO.getAddress())
                .gender(patientDTO.getGender())
                .medicalRecord(patientDTO.getMedicalRecord())
                .build();

        return patient;
    }
}
