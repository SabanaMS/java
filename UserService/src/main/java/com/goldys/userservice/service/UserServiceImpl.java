package com.goldys.userservice.service;

import com.goldys.userservice.exception.InvalidCredentialsException;
import com.goldys.userservice.exception.UserAlreadyExistsException;
import com.goldys.userservice.exception.UserNotFoundException;
import com.goldys.userservice.model.User;

/*
 * This class is implementing the UserService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
public class UserServiceImpl implements UserService {

    /*
     * Constructor Autowiring should be implemented for the UserRepository.
     */



    /*
     * Register a new User. Throw UserAlreadyExistsException if the user with specified
     * email already exists.
     */

    public User registerUser(User user) throws UserAlreadyExistsException {
        return null;
    }

    /*
     * Update an existing User by it's email. Throw UserNotFoundException if the
     * user with specified email does not exist.
     */

    public User updateProfile(User user) throws UserNotFoundException {
        return null;
    }

    /* Perform Login operation.
     * Retrieve an existing user by it's email. Throw UserNotFoundException if the
     * user with specified email does not exist.
     * Throw InvalidCredentialsException if the password does not match with the password
     * stored in DB.
     * Caching implementation should be done.
     */
    public User login(User user) throws UserNotFoundException, InvalidCredentialsException {
        return null;
    }

    /* Validate a particular user to check whether he has role "Executive".
     * Retrieve an existing user by his email and role.
     */
    @Override
    public boolean validateUser(String email) {
        return false;
    }

}
