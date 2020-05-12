package com.goldys.ticketservice.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldys.ticketservice.controller.TicketController;
import com.goldys.ticketservice.exception.TicketNotFoundException;
import com.goldys.ticketservice.model.Ticket;
import com.goldys.ticketservice.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TicketControllerTest {

    private MockMvc mockMvc;
    private Ticket ticket;
    private List<Ticket> ticketList;

    @Mock
    TicketService ticketService;
    @InjectMocks
    TicketController ticketController;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
        ticketList = new ArrayList<>();
        ticket = new Ticket("abc", true, null, null);
        ticket.setTicketId("1");
        ticketList.add(ticket);

    }

    @Test
    public void listAllTicketsSuccess() throws Exception {

        when(ticketService.listAllTickets()).thenReturn(ticketList);
        mockMvc.perform(get("/api/v1/ticketservice/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void listAllOpenTicketsSuccess() throws Exception {

        when(ticketService.listAllOpenTickets()).thenReturn(ticketList);
        mockMvc.perform(get("/api/v1/ticketservice/open/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTicketByTicketIdSuccess() throws Exception {

        when(ticketService.getTicketByTicketId("1")).thenReturn(ticket);
        mockMvc.perform(get("/api/v1/ticketservice/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTicketByTicketIdFailure() throws Exception {

        when(ticketService.getTicketByTicketId("2")).thenReturn(null);
        mockMvc.perform(get("/api/v1/ticketservice/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateTicketSuccess() throws Exception {

        when(ticketService.updateTicket(any())).thenReturn(ticket);
        mockMvc.perform(put("/api/v1/ticketservice/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(ticket)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void updateTicketFailure() throws Exception {

        when(ticketService.updateTicket(any())).thenThrow(TicketNotFoundException.class);
        mockMvc.perform(put("/api/v1/ticketservice/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(ticket)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

