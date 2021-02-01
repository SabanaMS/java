package com.goldys.enquiryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;
import com.goldys.enquiryservice.service.EnquiryService;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized
 * format. Starting from Spring 4 and above, we can use @RestController annotation which
 * is equivalent to using @Controller and @ResponseBody annotation
 *
 * Please note that the default path to use this controller should be "/api/v1/enquiryservice"
 */
@RestController
@RequestMapping("api/v1/enquiryservice")
public class EnquiryController {

    /*
     * Constructor Autowiring should be implemented for the Service Layer for Programs. Please note that we
     * should not create any object using the new keyword. Autowiring should be also
     * implemented for EnquiryServiceProxy interface which is used for implementing
     * client side load balanced.
     */
	private EnquiryService enquiryService;
	@Autowired
	public EnquiryController(EnquiryService enquiryService) {
		this.enquiryService = enquiryService;
	}

    /* API Version: 1.0
     * Define a handler method which will get us all enquiries.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If all enquiries found successfully.
     *
     * This handler method should map to the URL "/api/v1/enquiryservice/admin/" using HTTP GET
     * method.
     */
	@GetMapping("/admin/")
	public ResponseEntity<List<Enquiry>> getAllEnquiries() {
		return new ResponseEntity<List<Enquiry>>((List<Enquiry>)enquiryService.listAllEnquiries(), HttpStatus.OK);
	}


    /* API Version: 1.0
     * Define a handler method which will get us the Enquiry by a enquiryCode.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If the enquiry is found successfully.
     * 2. 404(NOT FOUND) - If the enquiry with specified enquiryCode is not found.
     *
     *
     * This handler method should map to the URL "/api/v1/enquiryservice/admin/{enquiryCode}" using HTTP GET
     * method, where "enquiryCode" should be replaced by a enquiryCode without {}
     *
     */

	@GetMapping("/admin/{enquiryCode}")
	public ResponseEntity<Enquiry> getEnquiryByEnquiryCode(@PathVariable("enquiryCode") String enquiryCode){
		try {
			return new ResponseEntity<Enquiry>((Enquiry)enquiryService.getEnquiryByCode(enquiryCode), HttpStatus.OK);
		} catch (EnquiryNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}

    /* API Version: 1.0
     * Define a handler method which will create a new enquiry by reading the Serialized
     * enquiry object from request body and save the enquiry in database.
     * Please note that the enquiryCode has to be unique and autogenerated.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 201(CREATED - In case of successful creation of the enquiry
     *
     * Please note that the same customer can raise multiple enquiries and hence
     * validation is not necessary.
     * This handler method should map to the URL "/api/v1/enquiryservice" using HTTP POST
     * method".
     */

	@PostMapping
    public ResponseEntity<Enquiry> addNewEnquiry(@RequestBody Enquiry enquiry) {
		Enquiry addedEnquiry = enquiryService.addNewEnquiry(enquiry);
        return new ResponseEntity<>(addedEnquiry, HttpStatus.CREATED);
    }

}
