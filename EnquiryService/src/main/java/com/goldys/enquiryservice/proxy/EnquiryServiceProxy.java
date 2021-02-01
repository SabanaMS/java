package com.goldys.enquiryservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;

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
@FeignClient(name = "enquiry-service")
@RibbonClient(name = "enquiry-service")
public interface EnquiryServiceProxy {

	@GetMapping("/api/v1/enquiryservice/admin/")
	ResponseEntity<?> listAllEnquiries();

	@GetMapping("/api/v1/enquiryservice/admin/{enquiryCode}")
	ResponseEntity<?> getEnquiryByCode(@PathVariable String enquiryCode) throws EnquiryNotFoundException;

	@PostMapping("/api/v1/enquiryservice")
	ResponseEntity<?> addNewEnquiry(@RequestBody Enquiry enquiry);

	@PutMapping("/api/v1/enquiryservice/admin/")
	ResponseEntity<?> updateEnquiry(@RequestBody Enquiry enquiry) throws EnquiryNotFoundException;

}
