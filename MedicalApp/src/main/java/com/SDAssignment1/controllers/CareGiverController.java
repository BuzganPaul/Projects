package com.SDAssignment1.controllers;

import com.SDAssignment1.dto.CareGiverDTO;
import com.SDAssignment1.services.CareGiverService;
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
@RequestMapping("/caregiver")
@Slf4j
public class CareGiverController {

    @Autowired
    private CareGiverService careGiverService;

    @GetMapping("/{username}")
    @ApiOperation(value = "Return caregiver details if found or null if not found.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "CareGiver was found")})
    @ResponseStatus(value = HttpStatus.OK)
    public CareGiverDTO getCareGiverByUserName(@PathVariable String username)
    {
        log.info("Caregiver request was found: " + username);
        return careGiverService.getCareGiverByUserName(username);
    }

    @GetMapping("/{pageSize}/{pageNumber}")
    @ApiOperation(value = "Return all careGivers details if found or null if not found in pages of different sizes.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "careGivers were found")})
    @ResponseStatus(value = HttpStatus.OK)
    public Page<CareGiverDTO> getAllcareGivers(@PathVariable int pageSize, @PathVariable int pageNumber)
    {
        log.info("Request for list of careGivers returned with page size "+ pageSize + " and page number "+pageNumber);
        return careGiverService.getAllCareGivers(pageSize, pageNumber);
    }

    @PostMapping
    @ApiOperation(value = "Add a new careGiver.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "careGiver was found")})
    @ResponseStatus(value = HttpStatus.CREATED)
    public CareGiverDTO getcareGiverByName(@Valid @RequestBody CareGiverDTO careGiverDTO)
    {
        log.info("Request for creating a new careGiver: " + careGiverDTO.toString());
        return careGiverService.createCareGiver(careGiverDTO);
    }

    @PutMapping({"/{username}"})
    @ApiOperation(value = "Update a careGiver.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "careGiver was updated")})
    @ResponseStatus(value = HttpStatus.OK)
    public CareGiverDTO updatecareGiverByName(@Valid @PathVariable String username, @RequestBody CareGiverDTO careGiverDTO)
    {
        log.info("Request update for caregiver: " + username + " with new data :" + careGiverDTO.toString());
        return careGiverService.updateCareGiver(username, careGiverDTO);
    }

    @DeleteMapping("/{username}")
    @ApiOperation(value = "Delete a careGiver.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Caregiver was deleted")})
    @ResponseStatus(value = HttpStatus.OK)
    public void deletecareGiverByName(@PathVariable String username)
    {
        log.info("Delete careGiver with name: " + username);
        careGiverService.deleteCareGiver(username);
    }

}