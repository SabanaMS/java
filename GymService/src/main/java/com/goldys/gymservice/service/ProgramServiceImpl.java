package com.goldys.gymservice.service;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;
import com.goldys.gymservice.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProgramServiceImpl implements ProgramService {


    @Autowired
    private ProgramRepository programRepository;


    @Override
    public Program addNewProgram(Program program) throws ProgramAlreadyExistsException {
        if(programRepository.findById(program.getProgramCode()).isPresent()) {
            throw new ProgramAlreadyExistsException();
        }

        program.setCurrentPrice();
        return programRepository.save(program);
    }


    @Override
    public Program updateExistingProgram(Program program) throws ProgramNotFoundException {
        if(programRepository.findById(program.getProgramCode()).isEmpty()) {
            throw new ProgramNotFoundException();
        }
        program.setCurrentPrice();
        return programRepository.save(program);
    }


    @Override
    public Program getProgramByCode(String programCode) throws ProgramNotFoundException {
        if(programRepository.findById(programCode).isEmpty()) {
            throw new ProgramNotFoundException();
        }
        return programRepository.findById(programCode).get();
    }



    @Override
    public List<Program> getProgramByDuration(int durationInMonths) {
        return programRepository.findByDurationInMonths(durationInMonths);
    }



    @Override
    public List<Program> listAllPrograms() {
        return programRepository.findAll();
    }


    @Override
    public List<Program> listAllActivePrograms() {
            return programRepository.findByIsActive(true);
    }
}
