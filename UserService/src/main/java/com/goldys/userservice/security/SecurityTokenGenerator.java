package com.goldys.userservice.security;

import com.goldys.userservice.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    /*
     * You Should not modify this interface. You have to implement these methods in
     * corresponding Impl classes
     */
    Map<String, String> generateToken(User user);
}
