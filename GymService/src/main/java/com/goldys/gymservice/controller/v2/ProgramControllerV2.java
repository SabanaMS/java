package com.goldys.gymservice.controller.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldys.gymservice.model.Program;
import com.goldys.gymservice.service.ProgramService;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized
 * format. Starting from Spring 4 and above, we can use @RestController annotation which
 * is equivalent to using @Controller and @ResponseBody annotation
 *
 * Please note that the default path to use this controller should be "/api/v2/gymservice"
 */
@RestController
@RequestMapping("/api/v2/gymservice")
public class ProgramControllerV2 {

    /*
     * Constructor Autowiring should be implemented for the Service Layer for Programs. Please note that we
     * should not create any object using the new keyword. Autowiring should be also
     * implemented for GymServiceProxy interface which is used for implementing
     * client side load balanced.
     */
	private ProgramService programService;
	ResponseEntity<?> responseEntity;
	@Autowired
	public ProgramControllerV2(ProgramService programService) {
		this.programService = programService;
	}


    /* API Version: 2.0
     * Define a handler method which will get us all active programs.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If all active programs found successfully.
     *
     * This handler method should map to the URL "/api/v2/gymservice/showAllActive" using HTTP GET
     * method.
     */
	@GetMapping("/showAllActive")
	public ResponseEntity<?> listAllPrograms() {
		List<Program> programs = programService.listAllActivePrograms();
		responseEntity = new ResponseEntity<>(programs, HttpStatus.OK);
		return responseEntity;
	}


    /* API Version: 2.0
     * Define a handler method which will get us all programs by its duration.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If all programs with matching duration found.
     *
     * This handler method should map to the URL "/api/v2/gymservice/{durationInMonths}" using HTTP GET
     * method where "durationInMonths" should be replaced by a durationInMonths without {}
     */
	@GetMapping("/{durationInMonths}")
	public ResponseEntity<?> findProgramByDuration(@PathVariable("durationInMonths") int durationInMonths) {
		List<Program> programs = programService.getProgramByDuration(durationInMonths);
		responseEntity = new ResponseEntity<>(programs, HttpStatus.OK);
		
		return responseEntity;
	}


}
