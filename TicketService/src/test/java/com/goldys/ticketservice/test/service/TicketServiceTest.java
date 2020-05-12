package com.goldys.ticketservice.test.service;

import com.goldys.ticketservice.exception.TicketNotFoundException;
import com.goldys.ticketservice.model.Ticket;
import com.goldys.ticketservice.repository.TicketRepository;
import com.goldys.ticketservice.service.TicketServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ResponseEntity responseEntity;


    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket;

    private List<Ticket> ticketList;

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        ticketList = new ArrayList<>();
        ticket = new Ticket("abc", true, null, null);
        ticket.setTicketId("1");
        ticketList.add(ticket);

    }

    @AfterEach
    public void tearDown() throws Exception {
        ticket = null;
    }


    @Test
    @Rollback(true)
    public void testUpdateTicketFailure() throws TicketNotFoundException {

        when(ticketRepository.findById(any())).thenReturn(Optional.ofNullable(null));

        assertThrows(TicketNotFoundException.class, () -> ticketService.updateTicket(ticket));

        verify(ticketRepository, times(1)).findById(any());
        verify(ticketRepository, times(0)).save(any());

    }

    @Test
    @Rollback(true)
    public void testGetAllTicketsSuccess() {

        when(ticketRepository.findAll()).thenReturn(ticketList);

        assertEquals(ticketList, ticketService.listAllTickets());

        verify(ticketRepository, times(1)).findAll();

    }

    @Test
    @Rollback(true)
    public void testGetAllOpenTicketsSuccess() {

        when(ticketRepository.findByIsOpen(true)).thenReturn(ticketList);

        assertEquals(ticketList, ticketService.listAllOpenTickets());

        verify(ticketRepository, times(1)).findByIsOpen(true);

    }

    @Test
    @Rollback(true)
    public void testGetTicketSuccess() throws TicketNotFoundException {

        when(ticketRepository.findById(any())).thenReturn(Optional.of(ticket));

        assertEquals(ticket, ticketService.getTicketByTicketId(ticket.getTicketId()));

        verify(ticketRepository, times(2)).findById(any());

    }

    @Test
    @Rollback(true)
    public void testGetTicketFailure() throws TicketNotFoundException {

        when(ticketRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class, () -> ticketService.getTicketByTicketId(ticket.getTicketId()));

        verify(ticketRepository, times(1)).findById(any());

    }
}
