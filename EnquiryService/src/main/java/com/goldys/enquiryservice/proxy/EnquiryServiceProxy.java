package com.goldys.enquiryservice.proxy;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
