package com.goldys.apigatewayservice.config;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.goldys.apigatewayservice.filter.JwtFilter;

/*
 * Annotate the class with @Configuration
 */
@Configuration
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

@Bean
    public FilterRegistrationBean jwtFilter() {
    	 /*FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
         filterRegistrationBean.setFilter(new JwtFilter());
         String[] urlPatterns = new String[4];
         urlPatterns[0] = "/gymservice/api/v1/gymservice/*";
         urlPatterns[1] ="/gymservice/api/v2/gymservice/*";
         urlPatterns[2] ="/enquiryservice/api/v1/enquiryservice/admin/*";
         urlPatterns[3] ="/ticketservice/api/v1/ticketservice/*";
         filterRegistrationBean.addUrlPatterns(urlPatterns);
         return filterRegistrationBean;*/
		 FilterRegistrationBean filter = new FilterRegistrationBean();
	     filter.setFilter(new JwtFilter());
	     filter.addUrlPatterns("/gymservice/api/v1/gymservice/*");
	     filter.addUrlPatterns("/gymservice/api/v2/gymservice/*");
	     filter.addUrlPatterns("/enquiryservice/api/v1/enquiryservice/admin/*");
	     filter.addUrlPatterns("/ticketservice/api/v1/ticketservice/*");
	
	     return filter;


    }

    /*
     *  Bean to be created for CorsFilter so that requests from any origin
     */
    public CorsFilter corsFilter() {
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();


        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("*", config);

        return new CorsFilter(source);

    }

}
