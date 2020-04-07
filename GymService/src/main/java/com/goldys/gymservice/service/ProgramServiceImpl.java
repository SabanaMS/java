package com.goldys.gymservice.service;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;
import com.goldys.gymservice.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * This class is implementing the ProgramService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
public class ProgramServiceImpl implements ProgramService {

    /*
	 * Autowiring should be implemented for the ProgramRepository.
	 */
    @Autowired
    private ProgramRepository programRepository;


    /*
	 * Add a new program. Throw ProgramAlreadyExistsException if the program with specified
	 * programCode already exists. Current price should be a calculated value and hence should
	 * not be given as an input. In case it is given, it has to be overridden.
	 */
    @Override
    public Program addNewProgram(Program program) throws ProgramAlreadyExistsException {
        if(programRepository.findById(program.getProgramCode()).isPresent()) {
            throw new ProgramAlreadyExistsException();
        }

        program.setCurrentPrice();
        return programRepository.save(program);
    }

    /*
     * Update an existing Program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist. Current price should be a calculated value and hence should
     * not be given as an input. In case it is given, it has to be overridden.
     */
    @Override
    public Program updateExistingProgram(Program program) throws ProgramNotFoundException {
        if(programRepository.findById(program.getProgramCode()).isEmpty()) {
            throw new ProgramNotFoundException();
        }
        program.setCurrentPrice();
        return programRepository.save(program);
    }


    /*
     * Retrieve an existing program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist.
     */
    @Override
    public Program getProgramByCode(String programCode) throws ProgramNotFoundException {
        if(programRepository.findById(programCode).isEmpty()) {
            throw new ProgramNotFoundException();
        }
        return programRepository.findById(programCode).get();
    }


    /*
     * Retrieve all existing programs by it's duration.
     */
    @Override
    public List<Program> getProgramByDuration(int durationInMonths) {
        return programRepository.findByDurationInMonths(durationInMonths);
    }


	/*
	 * Retrieve all existing programs
	 */
    @Override
    public List<Program> listAllPrograms() {
        return programRepository.findAll();
    }

    /*
     * Retrieve all existing programs which are active
     */
    @Override
    public List<Program> listAllActivePrograms() {
            return programRepository.findByIsActive(true);
    }
}
