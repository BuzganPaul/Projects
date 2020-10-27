package com.SDAssignment1.services;

import com.SDAssignment1.dto.DoctorDTO;
import com.SDAssignment1.entities.Doctor;
import com.SDAssignment1.repositories.DoctorRepository;
import com.SDAssignment1.services.utils.convertors.DoctorConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorDTO getDoctorByUserName(String username)
    {
        Doctor doctor = doctorRepository.findByUsername(username);
        if(doctor == null)
        {
            log.error("The is no such doctor in database.");
            throw new EntityNotFoundException("Doctor with username: " + username + " not found!");
        }
        log.info("Operation for retrieving a doctor.");
        return DoctorConvertor.entityToDTOConversion(doctor);
    }

    public DoctorDTO createDoctor(DoctorDTO doctorDTO)
    {
        Doctor doctor = doctorRepository.save(DoctorConvertor.dTOToEntityConversion(doctorDTO));
        log.info("Operation for creating a doctor.");
        return DoctorConvertor.entityToDTOConversion(doctor);
    }

    public Page<DoctorDTO> getAllDoctors(int pageSize, int pageNumber)
    {
        Page<DoctorDTO> doctorDTOList = doctorRepository.findAllDoctors(PageRequest.of(pageNumber, pageSize)).map(doctor -> DoctorConvertor.entityToDTOConversion(doctor));
        log.info("Operation for retrieving all doctors.");
        return doctorDTOList;
    }

    public DoctorDTO updateDoctor(String username, DoctorDTO doctorDTO)
    {
        Doctor doctor = doctorRepository.findByUsername(username);

        if(doctor == null)
        {
            log.error("The is no such doctor in database.");
            throw new EntityNotFoundException("Doctor with username: " + username + " not found!");
        }

        doctor.setUsername(doctorDTO.getUsername());
        doctor.setName(doctorDTO.getName());
        doctor.setAddress(doctorDTO.getAddress());
        doctor.setBirthDate(doctorDTO.getBirthDate());
        doctor.setGender(doctorDTO.getGender());
        doctor.setSpecialization(doctorDTO.getSpecialization());

        Doctor doctorAux = doctorRepository.save(doctor);

        log.info("Operation for updating a doctor.");

        return DoctorConvertor.entityToDTOConversion(doctorAux);
    }

    public void deleteDoctor(String username)
    {
        log.info("Operation for deleting a doctor.");
        doctorRepository.deleteByUsername(username);
    }

    public void deleteAllDoctors()
    {
        log.info("Operation for deleting all doctors.");
        doctorRepository.deleteAll();
    }

}
