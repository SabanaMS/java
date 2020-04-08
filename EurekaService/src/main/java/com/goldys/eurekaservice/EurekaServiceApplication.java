package com.goldys.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*Add the following annotations to the class which is the entry point of this application:

1. @EnableEurekaServer - This annotation defines that this application will act as
	the Netflix Eureka Service Registry

*/
@SpringBootApplication
public class EurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApplication.class, args);
	}

}
