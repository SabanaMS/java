package com.goldys.gymservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/*Add the following annotations to the class which is the entry point of this application:

1. @EnableEurekaClient - Once a Eureka service registry is started,
	this application will act as a client that both registers itself with the
	registry and uses the Spring Cloud EurekaClient to interrogate the registry
	for its own host and port. The @EnableEurekaClient activates the Netflix Eureka EurekaClient implementation.
2. @EnableFeignClients - With this annotation, we enable component scanning for interfaces that declare
    they are Feign clients.

*/
@SpringBootApplication
@EnableCaching
public class GymServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymServiceApplication.class, args);
	}

}
