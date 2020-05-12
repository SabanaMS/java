package com.goldys.ticketservice.commander.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldys.ticketservice.TicketServiceApplication;
import com.goldys.ticketservice.model.Ticket;
import com.goldys.ticketservice.repository.TicketRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TicketServiceApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application_test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;

    private Ticket ticket;

    private final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzb21zdWJocmFAZ21haWwuY29tIiwicm9sZSI6ImFkbWluIiwiaWF0IjoxNTg4MDk2NzM4fQ.79zL5FglhKiCOQpsMVeyvCrMWpXMeq7YNm7-zp4krU8";

    @BeforeEach
    public void setUp() {
        ticket = new Ticket("abc", true, null, null);
        ticket.setTicketId("10");
        ticketRepository.save(ticket);
    }

    @AfterEach
    public void tearDown() {
        ticketRepository.deleteById("10");
    }

    @Order(1)
    @Test
    public void listAllTicketsSuccess() throws Exception {

        mockMvc.perform(get("/api/v1/ticketservice/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(2)
    @Test
    public void listAllOpenTicketsSuccess() throws Exception {

        mockMvc.perform(get("/api/v1/ticketservice/open/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(3)
    @Test
    public void getTicketByTicketIdSuccess() throws Exception {

        mockMvc.perform(get("/api/v1/ticketservice/10").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Order(4)
    @Test
    public void getTicketByTicketIdFailure() throws Exception {

        mockMvc.perform(get("/api/v1/ticketservice/20").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Order(5)
    @Test
    public void updateTicketSuccess() throws Exception {

        mockMvc.perform(put("/api/v1/ticketservice/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(ticket)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }


    @Order(6)
    @Test
    public void updateTicketFailure() throws Exception {

        ticket.setTicketId("20");
        mockMvc.perform(put("/api/v1/ticketservice/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(ticket)))
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
