package com.goldys.gymservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;
import com.goldys.gymservice.repository.ProgramRepository;

/*
 * This class is implementing the ProgramService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
@CacheConfig(cacheNames="program")
public class ProgramServiceImpl implements ProgramService {

    /*
     * Constructor Autowiring should be implemented for the ProgramRepository.
     */
	private ProgramRepository programRepository;
	@Autowired
	public ProgramServiceImpl(ProgramRepository programRepository) {
		this.programRepository = programRepository;
	}

    /*
     * Add a new program. Throw ProgramAlreadyExistsException if the program with specified
     * programCode already exists. Current price should be a calculated value and hence should
     * not be given as an input. In case it is given, it has to be overridden.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again
     */
	@CacheEvict(value = "programs",allEntries = true)
    @Override
    public Program addNewProgram(Program program) throws ProgramAlreadyExistsException {
		Optional<Program> programExist = programRepository.findById(program.getProgramCode());
    	if (programExist.isPresent()) {
            throw new ProgramAlreadyExistsException();
        }
    	program.setCurrentPrice();
        return programRepository.save(program);
    }

    /*
     * Update an existing Program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist. Current price should be a calculated value and hence should
     * not be given as an input. In case it is given, it has to be overridden.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again.
     */
	@CacheEvict(value = "programs",allEntries = true)
    @Override
    public Program updateExistingProgram(Program program) throws ProgramNotFoundException {
		Optional<Program> existingPgm = programRepository.findById(program.getProgramCode());
		if (existingPgm.isEmpty()) {
            throw new ProgramNotFoundException();
        }
    	Program programExist = existingPgm.get();
    	programExist.setActive(program.isActive());    	
    	programExist.setDescription(program.getDescription());
    	programExist.setDiscountRate(program.getDiscountRate());
    	programExist.setDurationInMonths(program.getDurationInMonths());
    	programExist.setPrice(program.getPrice());
    	programExist.setProgramName(program.getProgramName());
    	program.setCurrentPrice();
        return programRepository.save(programExist);
    }


    /*
     * Retrieve an existing program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist.
     * Caching should be implemented to reduce method calls.
     */
	@Cacheable(value="programCache",key="#programCode")
    @Override
    public Program getProgramByCode(String programCode) throws ProgramNotFoundException {
		Optional<Program> programExist = programRepository.findById(programCode);
    	if (programExist.isEmpty()) {
            throw new ProgramNotFoundException();
        }
    	return programRepository.findById(programCode).get();
    }


    /*
     * Retrieve all existing programs by it's duration.
     * Caching should be implemented to reduce method calls.
     */
	@Cacheable(value="programsDuration",key="#durationInMonths")
    @Override
    public List<Program> getProgramByDuration(int durationInMonths) {
		List<Program> programs = programRepository.findByDurationInMonths(durationInMonths);
    	return programs;
    }


    /*
     * Retrieve all existing programs
     * Caching should be implemented to reduce method calls.
     */
	@Cacheable(value="programCache")
    @Override
    public List<Program> listAllPrograms() {
    	List<Program> programs = programRepository.findAll();
    	return programs;
    }

    /*
     * Retrieve all existing programs which are active.
     * Caching should be implemented to reduce method calls.
     */
	@Cacheable(value="programCache")
    @Override
    public List<Program> listAllActivePrograms() {
		List<Program> programs = programRepository.findByIsActiveTrue();
    	return programs;
    }


    /*
     * Delete an existing Program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again.
     */
	@CacheEvict(value = "programs",allEntries = true)
	@Override
    public void deleteProgram(String programCode) throws ProgramNotFoundException {
		Optional<Program> programExist = programRepository.findById(programCode);
    	if (programExist.isEmpty()) {
            throw new ProgramNotFoundException();
        } else {
        	programRepository.delete(programExist.get());
        }
    }


}
