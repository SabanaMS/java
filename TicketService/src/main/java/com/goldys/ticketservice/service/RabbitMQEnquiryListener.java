package com.goldys.ticketservice.service;

import com.goldys.ticketservice.model.Ticket;
import com.goldys.ticketservice.repository.TicketRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQEnquiryListener implements EnquiryListener {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private Ticket ticket;

    @RabbitListener(queues = "enquiry.new.queue")
    public void addNewTicket(String enquiryCode) {
        ticket = new Ticket();
        ticket.setEnquiryCode(enquiryCode);
        ticket.setOpen(true);

        ticketRepository.save(ticket);

    }
}
