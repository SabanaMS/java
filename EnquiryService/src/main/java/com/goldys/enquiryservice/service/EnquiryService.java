package com.goldys.enquiryservice.service;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;

import java.util.List;

public interface EnquiryService {

    /*
     * You Should not modify this interface. You have to implement these methods in
     * corresponding Impl classes
     *
     */
    Enquiry addNewEnquiry(Enquiry enquiry);

    List<Enquiry> listAllEnquiries();

    Enquiry getEnquiryByCode(String enquiryCode) throws EnquiryNotFoundException;

}
