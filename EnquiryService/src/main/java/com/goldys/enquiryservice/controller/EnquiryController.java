package com.goldys.enquiryservice.controller;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;
import com.goldys.enquiryservice.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/enquiryservice")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    /* Autowire EnquiryServiceProxy to allow Load Balancing */



    @Cacheable(value = "enquiry")
    @GetMapping("/admin/")
    public ResponseEntity<?> listAllEnquiries() {
        return new ResponseEntity<>(enquiryService.listAllEnquiries(), HttpStatus.OK);
    }


    @Cacheable(value = "enquiry", key = "#p0")
    @GetMapping("/admin/{enquiryCode}")
    public ResponseEntity<?> getEnquiryByCode(@PathVariable String enquiryCode) throws EnquiryNotFoundException {
        return new ResponseEntity<>(enquiryService.getEnquiryByCode(enquiryCode), HttpStatus.OK);
    }


    @CacheEvict(value = "enquiry",allEntries = true)
    @PostMapping
    public ResponseEntity<?> addNewEnquiry(@RequestBody Enquiry enquiry) {
        return new ResponseEntity<>(enquiryService.addNewEnquiry(enquiry), HttpStatus.CREATED);
    }

}
