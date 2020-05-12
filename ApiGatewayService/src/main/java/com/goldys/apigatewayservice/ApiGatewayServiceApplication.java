package com.goldys.apigatewayservice;

import org.springframework.boot.SpringApplication;

/*
Annotate this class with the following annotations:
1. @SpringBootApplication
2. @EnableZuulProxy - to mark this application as Zuul API Gateway
3. @EnableEurekaClient - to mark this application as a client of a Eureka Service Registry
*/

public class ApiGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayServiceApplication.class, args);
    }

}
