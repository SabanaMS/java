package com.goldys.userservice.security;

import com.goldys.userservice.model.User;

import java.util.Map;

/*
 * This class is implementing the SecurityTokenGenerator interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

    /*
     * This method is responsible for generating a JWT token with issuedAt, subject and secret
     */
    @Override
    public Map<String, String> generateToken(User user) {

     return null;
    }
}
