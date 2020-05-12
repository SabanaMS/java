package com.goldys.enquiryservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* Custom Exception already created and to be used */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Enquiry with specified details not found")
public class EnquiryNotFoundException extends Exception {

}
