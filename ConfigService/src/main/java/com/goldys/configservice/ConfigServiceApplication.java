package com.goldys.configservice;

import org.springframework.boot.SpringApplication;

/*
Annotate this class with the following annotations:
1. @SpringBootApplication
2. @EnableConfigServer - to mark this application as Spring Cloud Config Server
*/
public class ConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }

}
