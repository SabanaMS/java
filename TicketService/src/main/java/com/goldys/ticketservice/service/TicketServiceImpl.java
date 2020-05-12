package com.goldys.ticketservice.service;

import com.goldys.ticketservice.exception.TicketNotFoundException;
import com.goldys.ticketservice.exception.UserUnauthorizedException;
import com.goldys.ticketservice.model.Ticket;

import java.util.List;

/*
 * This class is implementing the TicketService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
public class TicketServiceImpl implements TicketService {

    /*
     * Constructor Autowiring should be implemented for the TicketRepository
     * and RestTemplate.
     */


    /*
     * Retrieve all existing ticket details.
     * Caching should be implemented to reduce method calls.
     */
    public List<Ticket> listAllTickets() {
        return null;
    }

    /*
     * Retrieve all existing ticket details which are in open status.
     * Caching should be implemented to reduce method calls.
     */

    public List<Ticket> listAllOpenTickets() {
        return null;
    }

    /*
     * Retrieve an existing ticket by it's ticketId. Throw TicketNotFoundException if the
     * ticket with specified ticketId does not exist.
     * Caching should be implemented to reduce method calls.
     */

    public Ticket getTicketByTicketId(String ticketId) throws TicketNotFoundException {
        return null;
    }


    /*
     * Update an existing Ticket by it's ticketId. Throw TicketNotFoundException if the
     * ticket with specified ticketId does not exist. Only a user with role "Executive" can update
     * the ticket details. To check the user authorization, API call needs to be made to UserService microservice
     * having the following details:
     * 1. Method: GET
     * 2. URI:
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again.
     *
     * @HystrixCommand annotation should be used here with a fallback method which
     * will be called if the API call fails.
     */
    public Ticket updateTicket(Ticket ticket) throws TicketNotFoundException, UserUnauthorizedException {
        return null;
    }

    /*
     * This method is the fallback method for updateTicket() method. In case
     * updateTicket() method could not connect to API, this method will be called
     * which will set the executiveEmail attribute value to be "default"
     */
    public Ticket updateTicketFallback(Ticket ticket) throws TicketNotFoundException, UserUnauthorizedException {
        return null;
    }
}
