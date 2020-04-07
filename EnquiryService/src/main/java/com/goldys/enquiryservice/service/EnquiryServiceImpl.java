package com.goldys.enquiryservice.service;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;
import com.goldys.enquiryservice.repository.EnquiryRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * This class is implementing the EnquiryService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
public class EnquiryServiceImpl implements EnquiryService {

    /*
     * Autowiring should be implemented for the EnquiryRepository.
     */
    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
     * Add a new enquiry
     */
    @Override
    public Enquiry addNewEnquiry(Enquiry enquiry) {

        Enquiry newEnquiry = enquiryRepository.save(enquiry);

        rabbitTemplate.convertAndSend("enquiry.new.queue",newEnquiry.getEnquiryCode());
        return newEnquiry;
    }


    /*
     * Retrieve all existing enquiries
     */
    @Override
    public List<Enquiry> listAllEnquiries() {
        return enquiryRepository.findAll();
    }


    /*
     * Retrieve an existing enquiry by it's enquiryCode. Throw EnquiryNotFoundException if the
     * enquiry with specified enquiryCode does not exist.
     */
    @Override
    public Enquiry getEnquiryByCode(String enquiryCode) throws EnquiryNotFoundException {
        if(enquiryRepository.findById(enquiryCode).isEmpty()) {
            throw new EnquiryNotFoundException();
        }
        return enquiryRepository.findById(enquiryCode).get();
    }

}
