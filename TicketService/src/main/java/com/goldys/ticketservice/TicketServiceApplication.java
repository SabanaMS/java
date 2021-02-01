package com.goldys.ticketservice;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.goldys.ticketservice.model.Ticket;


/*
Annotate this class with the following annotations:
1. @SpringBootApplication
2. @EnableEurekaClient - to mark this application as a client of a Eureka Service Registry
3. @EnableHystrix - to enable Hystrix fault tolerance in the application
*/
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix 
public class TicketServiceApplication {

    /* Create a bean to create a queue in RabbitMQ called "enquiry.new.queue" */
	@Bean
	public Queue hello() {
		return new Queue("enquiry.new.queue");
	}

	/* Create a bean for RestTemplate */
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
