package com.goldys.enquiryservice.service;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;

import java.util.List;

/*
 * This class is implementing the EnquiryService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
public class EnquiryServiceImpl implements EnquiryService {

    /*
     * Constructor Autowiring should be implemented for the EnquiryRepository
     * and RabbitTemplate.
     */


    /*
     * Add a new enquiry.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again.
     */

    public Enquiry addNewEnquiry(Enquiry enquiry) {

        return null;
    }


    /*
     * Retrieve all existing enquiries.
     * Caching should be implemented to reduce method calls.
     */

    public List<Enquiry> listAllEnquiries() {
        return null;
    }


    /*
     * Retrieve an existing enquiry by it's enquiryCode. Throw EnquiryNotFoundException if the
     * enquiry with specified enquiryCode does not exist.
     * Caching should be implemented to reduce method calls.
     */

    public Enquiry getEnquiryByCode(String enquiryCode) throws EnquiryNotFoundException {
        return null;
    }

}
