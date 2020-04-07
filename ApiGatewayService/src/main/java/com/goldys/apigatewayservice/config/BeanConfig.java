package com.goldys.apigatewayservice.config;

import org.springframework.context.annotation.Configuration;

/*
 * Annotate the class with @Configuration
 */
@Configuration
public class BeanConfig {

    /*
     *  Create a bean for FilterRegistrationBean.
     *  1. Register the JwtFilter
     *  2. add URL pattern for the following patterns so that any request for
     *     that URL pattern will be intercepted by the filter.
     *     - Gym Service("/api/v1/gymservice/*")
     *     - Gym Service("/api/v2/gymservice/*")
     *     - Enquiry Service("/api/v1/enquiryservice/admin/*")
     *     - Ticket Service(/api/v1/ticketservice/*)
     *
     *  Hint: As the Zuul API Gateway will be interrogating the Eureka
     *  Service discovery, hence the intercepting URL might need to be prefixed with
     *  some value.
     */


    /* Add CORS Filter to allow request from all external clients */



}
