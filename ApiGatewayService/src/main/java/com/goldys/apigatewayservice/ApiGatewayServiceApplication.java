package com.goldys.apigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/*
Annotate this class with the following annotations:
1. @SpringBootApplication
2. @EnableZuulProxy - to mark this application as Zuul API Gateway
3. @EnableEurekaClient - to mark this application as a client of a Eureka Service Registry
*/
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ApiGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayServiceApplication.class, args);
    }

}
