package com.goldys.enquiryservice;

import org.springframework.boot.SpringApplication;

/*
Annotate this class with the following annotations:
1. @SpringBootApplication
2. @EnableEurekaClient - to mark this application as a client of a Eureka Service Registry
3. @EnableCaching - to enable caching in the application
4. @EnableFeignClient - to mark this application as a feign client
*/

public class EnquiryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnquiryServiceApplication.class, args);
    }

}
