package com.goldys.gymservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;

/*
 * Add the annotations @FeignClient and @RibbonClient
 *
 * Create an interface where we declare the services we want to call. Please note that
 * Service Request mapping is same as the REST API URLs defined in the RestController.
 * Feign dynamically generates the implementation of the interface we created, so Feign
 * has to know which service to call beforehand. That's why we need to give a name for the
 * interface, which is the {Service-Id} of GymService. Now, Feign contacts the Eureka
 * server with this Service Id, resolves the actual IP/hostname of the GymService,
 * and calls the URL provided in Request Mapping.
 * */
@FeignClient(name = "gym-service")
@RibbonClient(name = "gym-service")
public interface GymServiceProxy {

	@GetMapping("/api/v1/gymservice")
    ResponseEntity<?> listAllPrograms();

    @GetMapping("/api/v2/gymservice/showAllActive")
    ResponseEntity<?> listAllActivePrograms();

    @GetMapping("/api/v2/gymservice/{durationInMonths}")
    ResponseEntity<?> getProgramByDuration(@PathVariable int durationInMonths) throws ProgramNotFoundException;

    @GetMapping("/v1/gymservice/{programCode}")
    ResponseEntity<?> getProgramByCode(@PathVariable String programCode) throws ProgramNotFoundException;

    @PostMapping("/v1/gymservice")
    ResponseEntity<?> addProgram(@RequestBody Program program) throws ProgramAlreadyExistsException;

    @PutMapping("/v1/gymservice")
    ResponseEntity<?> updateProgram(@RequestBody Program program) throws ProgramNotFoundException;

}
