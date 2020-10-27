package com.SDAssignment1.services.utils.convertors;

import com.SDAssignment1.dto.DoctorDTO;
import com.SDAssignment1.entities.Doctor;

public class DoctorConvertor {

    public static DoctorDTO entityToDTOConversion(Doctor doctor)
    {
        DoctorDTO doctorDTO =  DoctorDTO.builder()
                                .username(doctor.getUsername())
                                .password(null)
                                .userRole(doctor.getUserRole())
                                .name(doctor.getName())
                                .birthDate(doctor.getBirthDate())
                                .address(doctor.getAddress())
                                .gender(doctor.getGender())
                                .specialization(doctor.getSpecialization())
                                .build();

        return doctorDTO;
    }

    public static Doctor dTOToEntityConversion(DoctorDTO doctorDTO)
    {
        Doctor doctor = Doctor.builder()
                        .username(doctorDTO.getUsername())
                        .password(doctorDTO.getPassword())
                        .userRole(doctorDTO.getUserRole())
                        .name(doctorDTO.getName())
                        .birthDate(doctorDTO.getBirthDate())
                        .address(doctorDTO.getAddress())
                        .gender(doctorDTO.getGender())
                        .specialization(doctorDTO.getSpecialization())
                        .build();

        return doctor;
    }

}
