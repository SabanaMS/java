package com.goldys.userservice.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.goldys.userservice.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * This class is implementing the SecurityTokenGenerator interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

    /*
     * This method is responsible for generating a JWT token with issuedAt, subject and secret
     */
    @Override
    public Map<String, String> generateToken(User user) {
    	String jwtToken = "";
    	String secret = "goldysSecret";
        jwtToken =Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, secret).compact();
        Map<String,String> jwtTokenMap = new HashMap<String, String>();
        jwtTokenMap.put("token", jwtToken);
        jwtTokenMap.put("message", "User authentication successful");
        return jwtTokenMap;
    }
}
