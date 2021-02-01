package com.goldys.userservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.goldys.userservice.exception.InvalidCredentialsException;
import com.goldys.userservice.exception.UserAlreadyExistsException;
import com.goldys.userservice.exception.UserNotFoundException;
import com.goldys.userservice.model.User;

/*
 * Add the annotations @FeignClient and @RibbonClient
 *
 * Create an interface where we declare the services we want to call. Please note that
 * Service Request mapping is same as the REST API URLs defined in the RestController.
 * Feign dynamically generates the implementation of the interface we created, so Feign
 * has to know which service to call beforehand. That's why we need to give a name for the
 * interface, which is the {Service-Id} of UserService. Now, Feign contacts the Eureka
 * server with this Service Id, resolves the actual IP/hostname of the UserService,
 * and calls the URL provided in Request Mapping.
 * */
@FeignClient(name = "user-service")
@RibbonClient(name = "user-service")
public interface UserServiceProxy {

	@PostMapping("/api/v1/userservice")
    ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException;

    @PutMapping("/api/v1/userservice")
    ResponseEntity<?> updateUser(@RequestBody User user) throws UserNotFoundException;

    @PostMapping("/api/v1/userservice/login")
    ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentialsException, UserNotFoundException;

}
