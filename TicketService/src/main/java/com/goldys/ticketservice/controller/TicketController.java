package com.goldys.ticketservice.controller;

import com.goldys.ticketservice.exception.TicketNotFoundException;
import com.goldys.ticketservice.exception.UserUnauthorizedException;
import com.goldys.ticketservice.model.Ticket;
import com.goldys.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticketservice")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<?> listAllTickets() {

        return new ResponseEntity<>(ticketService.listAllTickets(), HttpStatus.OK);
    }

    @GetMapping("/open")
    public ResponseEntity<?> listAllOpenTickets() {

        return new ResponseEntity<>(ticketService.listAllOpenTickets(), HttpStatus.OK);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<?> getTicket(@PathVariable String ticketId) throws TicketNotFoundException {

        return new ResponseEntity<>(ticketService.getTicketByTicketId(ticketId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateTicket(@RequestBody Ticket ticket) throws TicketNotFoundException, UserUnauthorizedException {

        return new ResponseEntity<>(ticketService.updateTicket(ticket), HttpStatus.OK);
    }



}
