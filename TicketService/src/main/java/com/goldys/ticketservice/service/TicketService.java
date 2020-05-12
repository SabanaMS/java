package com.goldys.ticketservice.service;

import com.goldys.ticketservice.exception.TicketNotFoundException;
import com.goldys.ticketservice.exception.UserUnauthorizedException;
import com.goldys.ticketservice.model.Ticket;

import java.util.List;

public interface TicketService {

    /*
     * You Should not modify this interface. You have to implement these methods in
     * corresponding Impl classes
     */
    List<Ticket> listAllTickets();

    List<Ticket> listAllOpenTickets();

    Ticket getTicketByTicketId(String ticketId) throws TicketNotFoundException;

    Ticket updateTicket(Ticket ticket) throws TicketNotFoundException, UserUnauthorizedException;
}
