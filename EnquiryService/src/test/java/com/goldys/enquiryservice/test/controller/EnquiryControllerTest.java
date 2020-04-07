package com.goldys.enquiryservice.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EnquiryControllerTest {

    /*
     * Write positive and negative test cases to test all controller handler methods
     * to test whether they are working as per the specification.
     *
     * You need to implement mocking using Mockito API.
     */


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

