package com.goldys.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.goldys.userservice.exception.InvalidCredentialsException;
import com.goldys.userservice.exception.UserAlreadyExistsException;
import com.goldys.userservice.exception.UserNotFoundException;
import com.goldys.userservice.model.User;
import com.goldys.userservice.repository.UserRepository;

/*
 * This class is implementing the UserService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
@CacheConfig(cacheNames="user")
public class UserServiceImpl implements UserService {

    /*
     * Constructor Autowiring should be implemented for the UserRepository.
     */
	private UserRepository userRepository;
	@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


    /*
     * Register a new User. Throw UserAlreadyExistsException if the user with specified
     * email already exists.
     */
	@Cacheable(value = "users")
	@Override
	public User registerUser(User user) throws UserAlreadyExistsException {
		Optional<User> userResult = userRepository.findById(user.getEmail());
		if (userResult.isPresent()) {
			throw new UserAlreadyExistsException();
		}
		return userRepository.save(user);
	}

    /*
     * Update an existing User by it's email. Throw UserNotFoundException if the
     * user with specified email does not exist.
     */
	@CacheEvict(value = "users", key = "#user.email")
	@Override
	public User updateProfile(User user) throws UserNotFoundException {
		Optional<User> userResult = userRepository.findById(user.getEmail());
		if (userResult.isEmpty()) {
			throw new UserNotFoundException();
		}
		User updateUser = userResult.get();
		updateUser.setRole(user.getRole());
		updateUser.setPassword(user.getPassword());
		return userRepository.save(updateUser);
	}

    /* Perform Login operation.
     * Retrieve an existing user by it's email. Throw UserNotFoundException if the
     * user with specified email does not exist.
     * Throw InvalidCredentialsException if the password does not match with the password
     * stored in DB.
     * Caching implementation should be done.
     */
	@Cacheable(value = "users", key = "#p0", condition = "#p0!=null")
	@Override
	public User login(User user) throws UserNotFoundException, InvalidCredentialsException {
		Optional<User> userResult = userRepository.findById(user.getEmail());
		if (userResult.isEmpty()) {
			throw new UserNotFoundException();
		}
		User validUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (validUser == null) {
			throw new InvalidCredentialsException();
		}
		return validUser;
	}

    /* Validate a particular user to check whether he has role "Executive".
     * Retrieve an existing user by his email and role.
     */
    @Override
    public boolean validateUser(String email) {
    	Optional<User> userResult = userRepository.findById(email);
    	if (userResult.isPresent()) {
    		User user = userResult.get();
    		if("Executive".equals(user.getRole())) {
    			 return true;
    		}
    	}
        return false;
    }

}
