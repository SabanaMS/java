package com.goldys.apigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Add the following annotations to the class which is the entry point of this application:

1. @EnableEurekaClient - Once a Eureka service registry is started,
	this application will act as a client that both registers itself with the
	registry and uses the Spring Cloud EurekaClient to interrogate the registry
	for its own host and port. The @EnableEurekaClient activates the Netflix Eureka EurekaClient implementation.
2. @EnableZuulProxy - Added to Enable Netflix Zuul Proxy

*/
@SpringBootApplication

public class ApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

}
