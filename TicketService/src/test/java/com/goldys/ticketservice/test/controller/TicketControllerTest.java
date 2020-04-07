package com.goldys.ticketservice.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TicketControllerTest {



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

