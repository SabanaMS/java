package com.goldys.gymservice.controller;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;
import com.goldys.gymservice.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProgramController {


    @Autowired
    private ProgramService programService;

   /* Autowire GymServiceProxy to allow Load Balancing*/



    @Cacheable(value = "programs")
    @GetMapping("/v1/gymservice")
    public ResponseEntity<?> listAllPrograms() {
        return new ResponseEntity<>(programService.listAllPrograms(), HttpStatus.OK);
    }


    @Cacheable(value = "programs")
    @GetMapping("/v2/gymservice/showAllActive")
    public ResponseEntity<?> listAllActivePrograms() {
        return new ResponseEntity<>(programService.listAllActivePrograms(), HttpStatus.OK);
    }


    @GetMapping("/v2/gymservice/{durationInMonths}")
    public ResponseEntity<?> getProgramByDuration(@PathVariable int durationInMonths) throws ProgramNotFoundException {
        return new ResponseEntity<>(programService.getProgramByDuration(durationInMonths), HttpStatus.OK);
    }


    @Cacheable(value = "programs", key = "#p0")
    @GetMapping("/v1/gymservice/{programCode}")
    public ResponseEntity<?> getProgramByCode(@PathVariable String programCode) throws ProgramNotFoundException {
        return new ResponseEntity<>(programService.getProgramByCode(programCode), HttpStatus.OK);
    }


    @CacheEvict(value = "programs",allEntries = true)
    @PostMapping("/v1/gymservice")
    public ResponseEntity<?> addProgram(@RequestBody Program program) throws ProgramAlreadyExistsException {
        return new ResponseEntity<>(programService.addNewProgram(program), HttpStatus.CREATED);
    }

    @CacheEvict(value = "programs",allEntries = true)
    @PutMapping("/v1/gymservice")
    public ResponseEntity<?> updateProgram(@RequestBody Program program) throws ProgramNotFoundException {
        return new ResponseEntity<>(programService.updateExistingProgram(program), HttpStatus.OK);
    }

}
