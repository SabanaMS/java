package com.goldys.userservice.commander.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldys.userservice.controller.UserController;
import com.goldys.userservice.exception.InvalidCredentialsException;
import com.goldys.userservice.exception.UserAlreadyExistsException;
import com.goldys.userservice.exception.UserNotFoundException;
import com.goldys.userservice.model.User;
import com.goldys.userservice.security.SecurityTokenGenerator;
import com.goldys.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mockMvc;
    private User user;

    @Mock
    UserService userService;

    @Mock
    private SecurityTokenGenerator securityTokenGenerator;
    @InjectMocks
    UserController userController;

    private Map<String, String> map;

    @BeforeEach
    public void setUp() {

        map = new HashMap<>();
        map.put("token", "abc");
        map.put("message","User authentication successful");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User("john@abc.com","password","admin");
    }

    @Test
    public void registerUserSuccess() throws Exception {

        when(userService.registerUser(any())).thenReturn(user);
        mockMvc.perform(post("/api/v1/userservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void registerUserFailure() throws Exception {

        when(userService.registerUser(any())).thenThrow(UserAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/userservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateProfileSuccess() throws Exception {

        when(userService.updateProfile(any())).thenReturn(user);
        mockMvc.perform(put("/api/v1/userservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateFoodFailure() throws Exception {

        when(userService.updateProfile(any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(put("/api/v1/userservice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void userLoginSuccess() throws Exception {

        when(userService.login(any())).thenReturn(true);
        when(securityTokenGenerator.generateToken(user)).thenReturn(map);
        mockMvc.perform(post("/api/v1/userservice/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void userLoginNotFoundFailure() throws Exception {

        when(userService.login(any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(post("/api/v1/userservice/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void userLoginUnauthorizedFailure() throws Exception {

        when(userService.login(any())).thenThrow(InvalidCredentialsException.class);
        mockMvc.perform(post("/api/v1/userservice/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isUnauthorized()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void validateUserSuccess() throws Exception {

        when(userService.validate(any())).thenReturn(true);
        mockMvc.perform(get("/api/v1/userservice/abc").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void validateUserFailure() throws Exception {

        when(userService.validate(any())).thenReturn(false);
        mockMvc.perform(get("/api/v1/userservice/xyz").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
