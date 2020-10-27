package com.SDAssignment1.services.utils.convertors;


import com.SDAssignment1.dto.SideEffectDTO;
import com.SDAssignment1.entities.SideEffect;

public class SideEffectConvertor {

    public static SideEffectDTO entityToDTOConversion(SideEffect sideEffect)
    {
        return new SideEffectDTO(sideEffect.getName());
    }

    public static SideEffect dTOToEntityConversion(SideEffectDTO sideEffectDTO)
    {
        return new SideEffect(null, sideEffectDTO.getName());
    }
}
