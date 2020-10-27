package com.SDAssignment1.services;

import com.SDAssignment1.dto.PatientDTO;
import com.SDAssignment1.entities.Patient;
import com.SDAssignment1.repositories.PatientRepository;
import com.SDAssignment1.services.utils.convertors.PatientConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientDTO getPatientByUserName(String username)
    {
        Patient patient = patientRepository.findByUsername(username);
        if(patient == null)
        {
            log.error("The is no such Patient in database.");
            throw new EntityNotFoundException("Patient with username: " + username + " not found!");
        }
        log.info("Operation for retrieving a Patient.");
        return PatientConvertor.entityToDTOConversion(patient);
    }

    public PatientDTO createPatient(PatientDTO PatientDTO)
    {
        Patient patient = patientRepository.save(PatientConvertor.dTOToEntityConversion(PatientDTO));
        log.info("Operation for creating a Patient.");
        return PatientConvertor.entityToDTOConversion(patient);
    }

    public Page<PatientDTO> getAllPatients(int pageSize, int pageNumber)
    {
        Page<PatientDTO> PatientDTOList = patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize)).map(patient -> PatientConvertor.entityToDTOConversion(patient));
        log.info("Operation for retrieving all Patients.");
        return PatientDTOList;
    }

    public PatientDTO updatePatient(String username, PatientDTO PatientDTO)
    {
        Patient patient = patientRepository.findByUsername(username);

        if(patient == null)
        {
            log.error("The is no such Patient in database.");
            throw new EntityNotFoundException("Patient with username: " + username + " not found!");
        }

        patient.setUsername(PatientDTO.getUsername());
        patient.setName(PatientDTO.getName());
        patient.setAddress(PatientDTO.getAddress());
        patient.setBirthDate(PatientDTO.getBirthDate());
        patient.setGender(PatientDTO.getGender());
        patient.setMedicalRecord(PatientDTO.getMedicalRecord());

        Patient patientAux = patientRepository.save(patient);

        log.info("Operation for updating a Patient.");

        return PatientConvertor.entityToDTOConversion(patientAux);
    }

    public void deletePatient(String username)
    {
        log.info("Operation for deleting a Patient.");
        patientRepository.deleteByUsername(username);
    }

    public void deleteAllPatients()
    {
        log.info("Operation for deleting all Patients.");
        patientRepository.deleteAll();
    }

}