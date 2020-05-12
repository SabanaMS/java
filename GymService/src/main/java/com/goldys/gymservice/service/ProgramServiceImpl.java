package com.goldys.gymservice.service;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;

import java.util.List;

/*
 * This class is implementing the ProgramService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
public class ProgramServiceImpl implements ProgramService {

    /*
     * Constructor Autowiring should be implemented for the ProgramRepository.
     */



    /*
     * Add a new program. Throw ProgramAlreadyExistsException if the program with specified
     * programCode already exists. Current price should be a calculated value and hence should
     * not be given as an input. In case it is given, it has to be overridden.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again
     */

    public Program addNewProgram(Program program) throws ProgramAlreadyExistsException {
        return null;
    }

    /*
     * Update an existing Program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist. Current price should be a calculated value and hence should
     * not be given as an input. In case it is given, it has to be overridden.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again.
     */

    public Program updateExistingProgram(Program program) throws ProgramNotFoundException {
        return null;
    }


    /*
     * Retrieve an existing program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist.
     * Caching should be implemented to reduce method calls.
     */

    public Program getProgramByCode(String programCode) throws ProgramNotFoundException {
        return null;
    }


    /*
     * Retrieve all existing programs by it's duration.
     * Caching should be implemented to reduce method calls.
     */

    public List<Program> getProgramByDuration(int durationInMonths) {
        return null;
    }


    /*
     * Retrieve all existing programs
     * Caching should be implemented to reduce method calls.
     */

    public List<Program> listAllPrograms() {
        return null;
    }

    /*
     * Retrieve all existing programs which are active.
     * Caching should be implemented to reduce method calls.
     */

    public List<Program> listAllActivePrograms() {
        return null;
    }


    /*
     * Delete an existing Program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again.
     */

    public void deleteProgram(String programCode) throws ProgramNotFoundException {
        ;
    }


}
