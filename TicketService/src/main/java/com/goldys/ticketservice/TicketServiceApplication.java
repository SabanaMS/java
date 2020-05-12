package com.goldys.ticketservice;

import com.goldys.ticketservice.model.Ticket;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/*
Annotate this class with the following annotations:
1. @SpringBootApplication
2. @EnableEurekaClient - to mark this application as a client of a Eureka Service Registry
3. @EnableHystrix - to enable Hystrix fault tolerance in the application
*/

public class TicketServiceApplication {

    @Bean
    public Queue hello() {
        return new Queue("enquiry.new.queue");
    }

    @Bean
    public Ticket getTicket() {
        return new Ticket();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(TicketServiceApplication.class, args);
    }

}
