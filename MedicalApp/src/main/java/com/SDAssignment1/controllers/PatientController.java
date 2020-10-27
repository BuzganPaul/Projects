package com.SDAssignment1.controllers;

import com.SDAssignment1.dto.PatientDTO;
import com.SDAssignment1.services.PatientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/patient")
@Slf4j
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{username}")
    @ApiOperation(value = "Return Patient details if found or null if not found.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Patient was found")})
    @ResponseStatus(value = HttpStatus.OK)
    public PatientDTO getPatientByUserName(@PathVariable String username) {
        log.info("Patient request was found: " + username);
        return patientService.getPatientByUserName(username);
    }

    @GetMapping("/{pageSize}/{pageNumber}")
    @ApiOperation(value = "Return all Patients details if found or null if not found in pages of different sizes.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Patients were found")})
    @ResponseStatus(value = HttpStatus.OK)
    public Page<PatientDTO> getAllPatients(@PathVariable int pageSize, @PathVariable int pageNumber) {
        log.info("Request for list of Patients returned with page size " + pageSize + " and page number " + pageNumber);
        return patientService.getAllPatients(pageSize, pageNumber);
    }

    @PostMapping
    @ApiOperation(value = "Add a new Patient.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Patient was found")})
    @ResponseStatus(value = HttpStatus.CREATED)
    public PatientDTO getPatientByName(@Valid @RequestBody PatientDTO PatientDTO) {
        log.info("Request for creating a new Patient: " + PatientDTO.toString());
        return patientService.createPatient(PatientDTO);
    }

    @PutMapping({"/{username}"})
    @ApiOperation(value = "Update a Patient.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Patient was updated")})
    @ResponseStatus(value = HttpStatus.OK)
    public PatientDTO updatePatientByName(@Valid @PathVariable String username, @RequestBody PatientDTO PatientDTO) {
        log.info("Request update for Patient: " + username + " with new data :" + PatientDTO.toString());
        return patientService.updatePatient(username, PatientDTO);
    }

    @DeleteMapping("/{username}")
    @ApiOperation(value = "Delete a Patient.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Patient was deleted")})
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePatientByName(@PathVariable String username) {
        log.info("Delete Patient with name: " + username);
        patientService.deletePatient(username);
    }
}