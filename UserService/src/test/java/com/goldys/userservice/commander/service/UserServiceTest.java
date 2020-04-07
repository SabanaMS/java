package com.goldys.userservice.commander.service;

import com.goldys.userservice.exception.InvalidCredentialsException;
import com.goldys.userservice.exception.UserAlreadyExistsException;
import com.goldys.userservice.exception.UserNotFoundException;
import com.goldys.userservice.model.User;
import com.goldys.userservice.repository.UserRepository;
import com.goldys.userservice.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        user = new User("john@abc.com","password","admin");
    }

    @AfterEach
    public void tearDown() throws Exception {
        user = null;
    }

    @Test
    @Rollback(true)
    public void testRegisterUserSuccess() throws UserAlreadyExistsException {

        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(user);

        assertEquals(user, userService.registerUser(user));

        verify(userRepository, times(1)).findById(any());
        verify(userRepository, times(1)).save(any());

    }

    @Test
    @Rollback(true)
    public void testRegisterUserFailure() throws UserAlreadyExistsException {

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class,() -> userService.registerUser(user));

        verify(userRepository, times(1)).findById(any());
        verify(userRepository, times(0)).save(any());

    }



    @Test
    @Rollback(true)
    public void testUpdateProfileSuccess() throws UserNotFoundException {

        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(any())).thenReturn(user);

        assertEquals(user, userService.updateProfile(user));

        verify(userRepository, times(1)).findById(any());
        verify(userRepository, times(1)).save(any());

    }

    @Test
    @Rollback(true)
    public void testUpdateProfileFailure() throws UserNotFoundException {

        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(null));

        assertThrows(UserNotFoundException.class, () -> userService.updateProfile(user));

        verify(userRepository, times(1)).findById(any());
        verify(userRepository, times(0)).save(any());

    }

    @Test
    @Rollback(true)
    public void testUserLoginSuccess() throws UserNotFoundException, InvalidCredentialsException {

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userRepository.findByEmailAndPassword(anyString(),anyString())).thenReturn(user);

        assertEquals(true, userService.login(user));

        verify(userRepository, times(1)).findById(any());
        verify(userRepository, times(1)).findByEmailAndPassword(anyString(),anyString());

    }

    @Test
    @Rollback(true)
    public void testUserLoginNotFound() throws UserNotFoundException {

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,() -> userService.login(user));

        verify(userRepository, times(1)).findById(any());
        verify(userRepository, times(0)).findByEmailAndPassword(anyString(),anyString());

    }

    @Test
    @Rollback(true)
    public void testUserLoginUnauthorized() throws InvalidCredentialsException {

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        assertThrows(InvalidCredentialsException.class,() -> userService.login(user));

        verify(userRepository, times(1)).findById(any());
        verify(userRepository, times(1)).findByEmailAndPassword(anyString(),anyString());

    }

    @Test
    @Rollback(true)
    public void testValidateUserSuccess() {

        when(userRepository.findByEmailAndRole(any(),any())).thenReturn(user);

        assertEquals(true, userService.validate(user.getEmail()));

        verify(userRepository, times(1)).findByEmailAndRole(any(),any());
    }

    @Test
    @Rollback(true)
    public void testValidateUserFailure() {

        when(userRepository.findByEmailAndRole(any(),any())).thenReturn(null);

        assertEquals(false, userService.validate(user.getEmail()));

        verify(userRepository, times(1)).findByEmailAndRole(any(),any());
    }
}