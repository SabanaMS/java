package com.goldys.ticketservice.service;

/*
 * This class is implementing the EnquiryListener interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */

public class RabbitMQEnquiryListener implements EnquiryListener {

    /*
     * Constructor Autowiring should be implemented for the TicketRepository
     * and Ticket.
     */


    /*
     * This method should listen to RabbitMQ queue "enquiry.new.queue", on receiving a new message,
     * it should add a new ticket with the enquiryCode which should be coming from
     * the queue
     */

    public void addNewTicket(String enquiryCode) {

    }
}
