package com.goldys.ticketservice;

import org.springframework.boot.SpringApplication;


/*
Annotate this class with the following annotations:
1. @SpringBootApplication
2. @EnableEurekaClient - to mark this application as a client of a Eureka Service Registry
3. @EnableHystrix - to enable Hystrix fault tolerance in the application
*/
public class TicketServiceApplication {

    /* Create a bean to create a queue in RabbitMQ called "enquiry.new.queue" */


    /* Create a bean for RestTemplate */



    public static void main(String[] args) {
        SpringApplication.run(TicketServiceApplication.class, args);
    }

}
