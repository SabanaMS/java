package com.goldys.ticketservice.repository;

import com.goldys.ticketservice.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/*
 * This class is implementing the MongoRepository interface for Enquiry.
 * Annotate this class with @Repository annotation
 */
public interface TicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findByIsOpen(boolean open);
}
