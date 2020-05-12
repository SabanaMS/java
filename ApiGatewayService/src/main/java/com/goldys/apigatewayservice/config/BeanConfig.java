package com.goldys.apigatewayservice.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.filter.CorsFilter;

/*
 * Annotate the class with @Configuration
 */
public class BeanConfig {

    /*
     *  Create a bean for FilterRegistrationBean.
     *  1. Register the JwtFilter
     *  2. add URL pattern for following so that any request for
     *     that URL pattern will be intercepted by the filter:
     *      - '/gymservice/api/v1/gymservice/*'
     *      - '/gymservice/api/v2/gymservice/*'
     *      - '/enquiryservice/api/v1/enquiryservice/admin/*'
     *      - '/ticketservice/api/v1/ticketservice/*'
     */


    public FilterRegistrationBean jwtFilter() {

        return null;

    }

    /*
     *  Bean to be created for CorsFilter so that requests from any origin
     */
    public CorsFilter corsFilter() {
        return null;

    }

}
