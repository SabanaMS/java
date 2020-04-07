package com.goldys.ticketservice.service;

import com.goldys.ticketservice.exception.TicketNotFoundException;
import com.goldys.ticketservice.exception.UserUnauthorizedException;
import com.goldys.ticketservice.model.Ticket;
import com.goldys.ticketservice.repository.TicketRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    private ResponseEntity responseEntity;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Ticket> listAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> listAllOpenTickets() {
        return ticketRepository.findByIsOpen(true);
    }

    @Override
    public Ticket getTicketByTicketId(String ticketId) throws TicketNotFoundException {
        if(ticketRepository.findById(ticketId).isEmpty()) {
            throw new TicketNotFoundException();
        }
        return ticketRepository.findById(ticketId).get();
    }


    @Override
    @HystrixCommand(fallbackMethod = "updateTicketFallback")
    public Ticket updateTicket(Ticket ticket) throws TicketNotFoundException, UserUnauthorizedException {
        if(ticketRepository.findById(ticket.getTicketId()).isEmpty()) {
            throw new TicketNotFoundException();
        }

        responseEntity = restTemplate.getForEntity("http://localhost:9000/userservice/api/v1/userservice/"+ticket.getExecutiveEmail(), String.class);
        if(responseEntity.getBody().equals("false")) {
            throw new UserUnauthorizedException();
        }
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicketFallback(Ticket ticket) throws TicketNotFoundException, UserUnauthorizedException {
        ticket.setExecutiveEmail("default");
        return ticketRepository.save(ticket);
    }
}
