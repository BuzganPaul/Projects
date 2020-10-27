package com.SDAssignment1.controllers;

import com.SDAssignment1.dto.SideEffectDTO;
import com.SDAssignment1.services.SideEffectService;
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
@RequestMapping("/sideeffect")
@Slf4j
public class SideEffectController {

    @Autowired
    private SideEffectService sideEffectService;

    @GetMapping("/{name}")
    @ApiOperation(value = "Return SideEffect details if found or null if not found.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SideEffect was found")})
    @ResponseStatus(value = HttpStatus.OK)
    public SideEffectDTO getSideEffectByUserName(@PathVariable String name)
    {
        log.info("SideEffect request was found: " + name);
        return sideEffectService.getSideEffectByName(name);
    }

    @GetMapping("/{pageSize}/{pageNumber}")
    @ApiOperation(value = "Return all SideEffects details if found or null if not found in pages of different sizes.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SideEffects were found")})
    @ResponseStatus(value = HttpStatus.OK)
    public Page<SideEffectDTO> getAllSideEffects(@PathVariable int pageSize, @PathVariable int pageNumber)
    {
        log.info("Request for list of SideEffects returned with page size "+ pageSize + " and page number "+pageNumber);
        return sideEffectService.getAllSideEffect(pageSize, pageNumber);
    }

    @PostMapping
    @ApiOperation(value = "Add a new SideEffect.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "SideEffect was found")})
    @ResponseStatus(value = HttpStatus.CREATED)
    public SideEffectDTO getSideEffectByName(@Valid @RequestBody SideEffectDTO SideEffectDTO)
    {
        log.info("Request for creating a new Side Effect: " + SideEffectDTO.toString());
        return sideEffectService.createSideEffect(SideEffectDTO);
    }

    @PutMapping({"/{name}"})
    @ApiOperation(value = "Update a SideEffect.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "SideEffect was updated")})
    @ResponseStatus(value = HttpStatus.OK)
    public SideEffectDTO updateSideEffectByName(@Valid @PathVariable String name, @RequestBody SideEffectDTO SideEffectDTO)
    {
        log.info("Request update for SideEffect: " + name + " with new data :" + SideEffectDTO.toString());
        return sideEffectService.updateSideEffect(name, SideEffectDTO);
    }

    @DeleteMapping("/{name}")
    @ApiOperation(value = "Delete a SideEffect.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SideEffect was deleted")})
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteSideEffectByName(@PathVariable String name)
    {
        log.info("Delete SideEffect with name: " + name);
        sideEffectService.deleteSideEffect(name);
    }
}
