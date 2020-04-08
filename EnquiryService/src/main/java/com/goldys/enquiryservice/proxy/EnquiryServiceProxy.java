package com.goldys.enquiryservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

/*Add the @FeignClient and @RibbonClient annotations with a String value ("enquiry-service" in this case)
is an arbitrary client name, which is used to create a Ribbon load balancer */
@FeignClient(name = "enquiry-service")
@RibbonClient(name = "enquiry-service")
public interface EnquiryServiceProxy {

    /*  The interface should have the abstract methods which needs to be load balanced.
    In this case, it will be all the methods of the controller.
    */
}
