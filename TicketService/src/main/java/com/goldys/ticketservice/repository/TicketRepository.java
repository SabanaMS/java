package com.goldys.ticketservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.goldys.ticketservice.model.Ticket;

/*
 * This class is implementing the MongoRepository interface for Enquiry.
 * Annotate this class with @Repository annotation
 */
@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findByIsOpen(boolean open);
}
