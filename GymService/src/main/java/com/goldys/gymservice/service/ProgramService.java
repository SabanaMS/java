package com.goldys.gymservice.service;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;

import java.util.List;

public interface ProgramService {

    /*
     * You Should not modify this interface. You have to implement these methods in
     * corresponding Impl classes
     */

    Program addNewProgram(Program program) throws ProgramAlreadyExistsException;

    Program updateExistingProgram(Program program) throws ProgramNotFoundException;

    Program getProgramByCode(String programCode) throws ProgramNotFoundException;

    List<Program> getProgramByDuration(int durationInMonths);

    List<Program> listAllPrograms();

    List<Program> listAllActivePrograms();

    void deleteProgram(String programCode) throws ProgramNotFoundException;

}
