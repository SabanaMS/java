package com.goldys.ticketservice.controller;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized
 * format. Starting from Spring 4 and above, we can use @RestController annotation which
 * is equivalent to using @Controller and @ResponseBody annotation
 *
 * Please note that the default path to use this controller should be "/api/v1/ticketservice"
 */

public class TicketController {

    /*
     * Constructor Autowiring should be implemented for the Service Layer for Tickets. Please note that we
     * should not create any object using the new keyword.
     */


    /* API Version: 1.0
     * Define a handler method which will get us all tickets.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If all ticket found successfully.
     *
     * This handler method should map to the URL "/api/v1/ticketservice/" using HTTP GET
     * method.
     */


    /* API Version: 1.0
     * Define a handler method which will get us all open tickets.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If all ticket found successfully.
     *
     * This handler method should map to the URL "/api/v1/ticketservice/open/" using HTTP GET
     * method.
     */



    /* API Version: 1.0
     * Define a handler method which will get us the Ticket by ticketId.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If the ticket is found successfully.
     * 2. 404(NOT FOUND) - If the ticket with specified ticketId is not found.
     *
     *
     * This handler method should map to the URL "/api/v1/ticketservice/{ticketId}" using HTTP GET
     * method, where "ticketId" should be replaced by a ticketId without {}
     *
     */



    /* API Version: 1.0
     * Define a handler method which will update a specific ticket by reading the
     * Serialized object from request body and save the updated ticket in
     * ticket table in database and handle TicketNotFoundException, UserUnauthorizedException as well.
     *
     * This handler method should return any one of the status
     * messages basis on different situations:
     * 1. 200(OK) - If the ticket is updated successfully.
     * 2. 404(NOT FOUND) - If the ticket with specified ticketId is not found.
     * 3. 401(UNATHORIZED) - If the executiveEmail is does not belong to the role "Executive".
     *
     * This handler method should map to the URL "/api/v1/ticketservice" using HTTP PUT
     * method.
     */



}
