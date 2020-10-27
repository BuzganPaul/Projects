package com.SDAssignment1.services;

import com.SDAssignment1.dto.SideEffectDTO;
import com.SDAssignment1.entities.SideEffect;
import com.SDAssignment1.repositories.SideEffectRepository;
import com.SDAssignment1.services.utils.convertors.SideEffectConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class SideEffectService {

    @Autowired
    private SideEffectRepository sideEffectRepository;

    public SideEffectDTO getSideEffectByName(String name)
    {
        SideEffect sideEffect = sideEffectRepository.findByName(name);
        if(sideEffect == null)
        {
            log.error("The is no such Side Effect in database.");
            throw new EntityNotFoundException("Side Effect with name: " + name + " not found!");
        }
        log.info("Operation for retrieving a side effect.");
        return SideEffectConvertor.entityToDTOConversion(sideEffect);
    }

    public SideEffectDTO createSideEffect(SideEffectDTO sideEffectDTO)
    {
        SideEffect sideEffect = sideEffectRepository.save(SideEffectConvertor.dTOToEntityConversion(sideEffectDTO));
        log.info("Operation for creating a side effect.");
        return SideEffectConvertor.entityToDTOConversion(sideEffect);
    }

    public Page<SideEffectDTO> getAllSideEffect(int pageSize, int pageNumber)
    {
        Page<SideEffectDTO> sideEffectDTOList = sideEffectRepository.findAllSideEffect(PageRequest.of(pageNumber, pageSize)).map(sideEffect -> SideEffectConvertor.entityToDTOConversion(sideEffect));
        log.info("Operation for retrieving all side effects.");
        return sideEffectDTOList;
    }

    public SideEffectDTO updateSideEffect(String name, SideEffectDTO sideEffectDTO)
    {
        SideEffect sideEffect = sideEffectRepository.findByName(name);

        if(sideEffect== null)
        {
            log.error("The is no such side effect in database.");
            throw new EntityNotFoundException("Side effect with username: " + name + " not found!");
        }

        sideEffect.setName(sideEffectDTO.getName());

        SideEffect sideEffectAux = sideEffectRepository.save(sideEffect);

        log.info("Operation for updating a side effect.");

        return SideEffectConvertor.entityToDTOConversion(sideEffectAux);
    }

    public void deleteSideEffect(String name)
    {
        log.info("Operation for deleting a side effect.");
        sideEffectRepository.deleteByName(name);
    }

    public void deleteAllSideEffect()
    {
        log.info("Operation for deleting all side effects.");
        sideEffectRepository.deleteAll();
    }
}
