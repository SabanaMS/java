package com.goldys.ticketservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.goldys.ticketservice.exception.TicketNotFoundException;
import com.goldys.ticketservice.exception.UserUnauthorizedException;
import com.goldys.ticketservice.model.Ticket;
import com.goldys.ticketservice.repository.TicketRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/*
 * This class is implementing the TicketService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
@CacheConfig(cacheNames="ticket")
public class TicketServiceImpl implements TicketService {

    /*
     * Constructor Autowiring should be implemented for the TicketRepository
     * and RestTemplate.
     */
	private TicketRepository ticketRepository;
	private RestTemplate restTemplate;
	 private ResponseEntity responseEntity;
	@Autowired
	public TicketServiceImpl(TicketRepository ticketRepository, RestTemplate restTemplate) {
		this.ticketRepository = ticketRepository;
		this.restTemplate = restTemplate;
	}

    /*
     * Retrieve all existing ticket details.
     * Caching should be implemented to reduce method calls.
     */

    
	@Cacheable(value="ticketCache")
    @Override
	public List<Ticket> listAllTickets() {
		List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    /*
     * Retrieve all existing ticket details which are in open status.
     * Caching should be implemented to reduce method calls.
     */
	@Cacheable(value="ticketCache")
    @Override
    public List<Ticket> listAllOpenTickets() {
		List<Ticket> tickets = ticketRepository.findByIsOpen(true);
        return tickets;
    }

    /*
     * Retrieve an existing ticket by it's ticketId. Throw TicketNotFoundException if the
     * ticket with specified ticketId does not exist.
     * Caching should be implemented to reduce method calls.
     */
	@Cacheable(value="ticketCache")
    @Override
    public Ticket getTicketByTicketId(String ticketId) throws TicketNotFoundException {
		Optional<Ticket> ticketExist = ticketRepository.findById(ticketId);
		if (ticketExist.isEmpty()) {
			throw new TicketNotFoundException();
		}
        return ticketExist.get();
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
	@HystrixCommand
	@CacheEvict(value = "ticketCache",allEntries = true)
    @Override
    public Ticket updateTicket(Ticket ticket) throws TicketNotFoundException, UserUnauthorizedException {
		Optional<Ticket> ticketsAvailable = ticketRepository.findById(ticket.getTicketId());
		if(ticketsAvailable.isEmpty()) {
			throw new TicketNotFoundException();
		}
		responseEntity = restTemplate.getForEntity(
				"http://localhost:9000/userservice/api/v1/userservice/" + ticket.getExecutiveEmail(), String.class);
		if (responseEntity.getBody().equals("false")) {
			throw new UserUnauthorizedException();
		}
		return ticketRepository.save(ticket);

    }

    /*
     * This method is the fallback method for updateTicket() method. In case
     * updateTicket() method could not connect to API, this method will be called
     * which will set the executiveEmail attribute value to be "default"
     */
    public Ticket updateTicketFallback(Ticket ticket) throws TicketNotFoundException, UserUnauthorizedException {
    	Optional<Ticket> ticketsAvailable = ticketRepository.findById(ticket.getTicketId());
		if(ticketsAvailable.isEmpty()) {
			throw new TicketNotFoundException();
		}
		Ticket ticketSaved = ticketsAvailable.get();
		ticketSaved.setExecutiveEmail("default");
		responseEntity = restTemplate.getForEntity(
				"http://localhost:9000/userservice/api/v1/userservice/" + ticketSaved.getExecutiveEmail(), String.class);
		if (responseEntity.getBody().equals("false")) {
			throw new UserUnauthorizedException();
		}
        return ticketRepository.save(ticketSaved);

    }
}
