package com.goldys.userservice.service;

import com.goldys.userservice.exception.InvalidCredentialsException;
import com.goldys.userservice.exception.UserAlreadyExistsException;
import com.goldys.userservice.exception.UserNotFoundException;
import com.goldys.userservice.model.User;
import com.goldys.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }


    @Override
    public User updateProfile(User user) throws UserNotFoundException {
        if(userRepository.findById(user.getEmail()).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.save(user);
    }


    @Override
    public boolean login(User user) throws UserNotFoundException, InvalidCredentialsException {
        if(userRepository.findById(user.getEmail()).isEmpty()){
            throw new UserNotFoundException();
        }
        if(userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword())==null){
            throw new InvalidCredentialsException();
        }
        return true;
    }


    /*
     * Validates an user exists with a email ID which is supplied as parameter. Also, this method will check whether
     * the user is having the role "Executive". If both conditions are true, then it should return true, else it should
     * return false.
     */
    @Override
    public boolean validate(String email) {
        return false;
    }
}
