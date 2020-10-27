package com.SDAssignment1.services;

import com.SDAssignment1.dto.CareGiverDTO;
import com.SDAssignment1.dto.PatientDTO;
import com.SDAssignment1.entities.CareGiver;
import com.SDAssignment1.entities.Patient;
import com.SDAssignment1.repositories.CareGiverRepository;
import com.SDAssignment1.repositories.PatientRepository;
import com.SDAssignment1.services.utils.convertors.CareGiverConvertor;
import com.SDAssignment1.services.utils.convertors.PatientConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CareGiverService {

    @Autowired
    CareGiverRepository careGiverRepository;

    @Autowired
    PatientRepository patientRepository;

    public CareGiverDTO getCareGiverByUserName(String username)
    {
        CareGiver careGiver = careGiverRepository.findByUsername(username);
        if(careGiver == null)
        {
            log.error("The is no such CareGiver in database.");
            throw new EntityNotFoundException("CareGiver with username: " + username + " not found!");
        }
        log.info("Operation for retrieving a CareGiver.");
        return CareGiverConvertor.entityToDTOConversion(careGiver);
    }

    public CareGiverDTO createCareGiver(CareGiverDTO CareGiverDTO)
    {
        CareGiver CareGiver = careGiverRepository.save(CareGiverConvertor.dTOToEntityConversion(CareGiverDTO));
        log.info("Operation for creating a CareGiver.");
        return CareGiverConvertor.entityToDTOConversion(CareGiver);
    }

    public Page<CareGiverDTO> getAllCareGivers(int pageSize, int pageNumber)
    {
        Page<CareGiverDTO> careGiverDTOList = careGiverRepository.findAllCareGivers(PageRequest.of(pageNumber, pageSize)).map(careGiver -> CareGiverConvertor.entityToDTOConversion(careGiver));
        log.info("Operation for retrieving all CareGivers.");
        return careGiverDTOList;
    }

    public Page<PatientDTO> getAllPatients(int pageSize, int pageNumber, String careGiverUsername)
    {
        CareGiver careGiver = careGiverRepository.findByUsername(careGiverUsername);
        List<PatientDTO> patientDTOList = new ArrayList<>();

        for(Patient aux : careGiver.getPatientsList())
        {
            patientDTOList.add(PatientConvertor.entityToDTOConversion(aux));
        }

        Page<PatientDTO> patientDTOListPage = new PageImpl<>(patientDTOList);
        log.info("Operation for retrieving all patients for a specific caregiver.");
        return patientDTOListPage;
    }

    public CareGiverDTO updateCareGiver(String username, CareGiverDTO CareGiverDTO)
    {
        CareGiver careGiver = careGiverRepository.findByUsername(username);

        if(careGiver == null)
        {
            log.error("The is no such CareGiver in database.");
            throw new EntityNotFoundException("CareGiver with username: " + username + " not found!");
        }

        careGiver.setUsername(CareGiverDTO.getUsername());
        careGiver.setName(CareGiverDTO.getName());
        careGiver.setAddress(CareGiverDTO.getAddress());
        careGiver.setBirthDate(CareGiverDTO.getBirthDate());
        careGiver.setGender(CareGiverDTO.getGender());

        CareGiver careGiverAux = careGiverRepository.save(careGiver);

        log.info("Operation for updating a CareGiver.");

        return CareGiverConvertor.entityToDTOConversion(careGiverAux);
    }

    public PatientDTO addPatientToCareGiver(String careGiverUsername, String patientUsername)
    {
        CareGiver careGiver = careGiverRepository.findByUsername(careGiverUsername);
        Patient patient = patientRepository.findByUsername(patientUsername);

        if(careGiver == null)
        {
            log.error("The is no such CareGiver in database.");
            throw new EntityNotFoundException("CareGiver with username: " + careGiverUsername + " not found!");
        }

        if(patient == null)
        {
            log.error("The is no such Pateint in database.");
            throw new EntityNotFoundException("Patient with username: " + patientUsername + " not found!");
        }

        careGiver.getPatientsList().add(patient);
        careGiverRepository.save(careGiver);
        return PatientConvertor.entityToDTOConversion(patient);
    }

    public PatientDTO removePatientToCareGiver(String careGiverUsername, String patientUsername)
    {
        CareGiver careGiver = careGiverRepository.findByUsername(careGiverUsername);
        Patient patient = patientRepository.findByUsername(patientUsername);

        if(careGiver == null)
        {
            log.error("The is no such CareGiver in database.");
            throw new EntityNotFoundException("CareGiver with username: " + careGiverUsername + " not found!");
        }

        if(patient == null)
        {
            log.error("The is no such Pateint in database.");
            throw new EntityNotFoundException("Patient with username: " + patientUsername + " not found!");
        }

        careGiver.getPatientsList().remove(patient);
        careGiverRepository.save(careGiver);
        return PatientConvertor.entityToDTOConversion(patient);
    }

    public void deleteCareGiver(String username)
    {
        log.info("Operation for deleting a CareGiver.");
        careGiverRepository.deleteByUsername(username);
    }

    public void deleteAllCareGivers()
    {
        log.info("Operation for deleting all CareGivers.");
        careGiverRepository.deleteAll();
    }


}
