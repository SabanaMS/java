package com.goldys.userservice.controller;

import com.goldys.userservice.exception.InvalidCredentialsException;
import com.goldys.userservice.exception.UserAlreadyExistsException;
import com.goldys.userservice.exception.UserNotFoundException;
import com.goldys.userservice.model.User;
import com.goldys.userservice.security.SecurityTokenGenerator;
import com.goldys.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/userservice")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    /* Autowire UserServiceProxy to allow Load Balancing */

    @Cacheable(value = "users")
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }



    @CacheEvict(value = "users",key = "#user.email")
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateProfile(user), HttpStatus.OK);
    }


    @Cacheable(value = "users", key = "#p0", condition="#p0!=null")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentialsException, UserNotFoundException {

        Map<String,String> map = null;

        if(userService.login(user)) {
            map = securityTokenGenerator.generateToken(user);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    /* API Version: 1.0
     * Define a handler method which will validate a user by reading the emaild ID
     * which is sent as a PathVariable.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - In case of presence of a user having the specified email ID
     *
     * This handler method should map to the URL "/api/v1/userservice/{email}" using HTTP GET
     * method" where "email" will be given as PathVariable.
     */


}
