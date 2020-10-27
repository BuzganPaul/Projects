package com.SDAssignment1.services.utils.convertors;

import com.SDAssignment1.dto.CareGiverDTO;
import com.SDAssignment1.entities.CareGiver;

public class CareGiverConvertor {

    public static CareGiverDTO entityToDTOConversion(CareGiver CareGiver)
    {
        CareGiverDTO careGiverDTO =  CareGiverDTO.builder()
                .username(CareGiver.getUsername())
                .password(null)
                .userRole(CareGiver.getUserRole())
                .name(CareGiver.getName())
                .birthDate(CareGiver.getBirthDate())
                .address(CareGiver.getAddress())
                .gender(CareGiver.getGender())
                .build();

        return careGiverDTO;
    }

    public static CareGiver dTOToEntityConversion(CareGiverDTO careGiverDTO)
    {
        CareGiver careGiver = CareGiver.builder()
                            .username(careGiverDTO.getUsername())
                            .password(careGiverDTO.getPassword())
                            .userRole(careGiverDTO.getUserRole())
                            .name(careGiverDTO.getName())
                            .birthDate(careGiverDTO.getBirthDate())
                            .address(careGiverDTO.getAddress())
                            .gender(careGiverDTO.getGender())
                            .build();

        return careGiver;
    }
}
