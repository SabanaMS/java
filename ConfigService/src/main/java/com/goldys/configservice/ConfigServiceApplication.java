package com.goldys.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/*
Spring Cloud Config provides server and client-side support for externalized
configuration in a distributed system. With the Config Server you have a central
place to manage external properties for applications across all environments.

Add the annotation @EnableConfigServer, which defines that this application will
work as the externalized configuration server.
*/


@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServiceApplication.class, args);
	}

}
