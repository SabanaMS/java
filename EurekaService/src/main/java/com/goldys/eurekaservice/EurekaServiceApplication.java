package com.goldys.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
Annotate this class with the following annotations:
1. @SpringBootApplication
2. @EnableEurekaServer - to mark this application as the Eureka Service Registry Server
*/
@SpringBootApplication
@EnableEurekaServer 
public class EurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class, args);
    }

}
