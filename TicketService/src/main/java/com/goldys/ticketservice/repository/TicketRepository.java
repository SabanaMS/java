package com.goldys.ticketservice.repository;

import com.goldys.ticketservice.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

    public List<Ticket> findByIsOpen(boolean open);
}
