package com.goldys.enquiryservice.proxy;

/*
 * Add the annotations @FeignClient and @RibbonClient
 *
 * Create an interface where we declare the services we want to call. Please note that
 * Service Request mapping is same as the REST API URLs defined in the RestController.
 * Feign dynamically generates the implementation of the interface we created, so Feign
 * has to know which service to call beforehand. That's why we need to give a name for the
 * interface, which is the {Service-Id} of EnquiryService. Now, Feign contacts the Eureka
 * server with this Service Id, resolves the actual IP/hostname of the EnquiryService,
 * and calls the URL provided in Request Mapping.
 * */
public interface EnquiryServiceProxy {


}
