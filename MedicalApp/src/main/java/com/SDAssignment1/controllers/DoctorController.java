package com.SDAssignment1.controllers;

import com.SDAssignment1.dto.DoctorDTO;
import com.SDAssignment1.services.DoctorService;
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
@RequestMapping("/doctor")
@Slf4j
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/{username}")
    @ApiOperation(value = "Return doctor details if found or null if not found.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Doctor was found")})
    @ResponseStatus(value = HttpStatus.OK)
    public DoctorDTO getDoctorByUserName(@PathVariable String username)
    {
        log.info("Doctor request was found: " + username);
        return doctorService.getDoctorByUserName(username);
    }

    @GetMapping("/{pageSize}/{pageNumber}")
    @ApiOperation(value = "Return all doctors details if found or null if not found in pages of different sizes.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Doctors were found")})
    @ResponseStatus(value = HttpStatus.OK)
    public Page<DoctorDTO> getAllDoctors(@PathVariable int pageSize, @PathVariable int pageNumber)
    {
        log.info("Request for list of doctors returned with page size "+ pageSize + " and page number "+pageNumber);
        return doctorService.getAllDoctors(pageSize, pageNumber);
    }

    @PostMapping
    @ApiOperation(value = "Add a new doctor.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Doctor was found")})
    @ResponseStatus(value = HttpStatus.CREATED)
    public DoctorDTO getDoctorByName(@Valid @RequestBody DoctorDTO doctorDTO)
    {
        log.info("Request for creating a new doctor: " + doctorDTO.toString());
        return doctorService.createDoctor(doctorDTO);
    }

    @PutMapping({"/{username}"})
    @ApiOperation(value = "Update a doctor.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Doctor was updated")})
    @ResponseStatus(value = HttpStatus.OK)
    public DoctorDTO updateDoctorByName(@Valid @PathVariable String username, @RequestBody DoctorDTO doctorDTO)
    {
        log.info("Request update for doctor: " + username + " with new data :" + doctorDTO.toString());
        return doctorService.updateDoctor(username, doctorDTO);
    }

    @DeleteMapping("/{username}")
    @ApiOperation(value = "Delete a doctor.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Doctor was deleted")})
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteDoctorByName(@PathVariable String username)
    {
        log.info("Delete doctor with name: " + username);
        doctorService.deleteDoctor(username);
    }

}
