package com.goldys.enquiryservice.service;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;
import com.goldys.enquiryservice.repository.EnquiryRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private EnquiryRepository enquiryRepository;

    /*
     * Autowiring should be implemented for the EnquiryRepository.
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
     * Add a new enquiry
     *
     *
     */
    @Override
    public Enquiry addNewEnquiry(Enquiry enquiry) {

        Enquiry newEnquiry = enquiryRepository.save(enquiry);
        /* Once a new enquiry is added, the enquiryCode will be generated, which should
         * be published to the queue "enquiry.new.queue" which will be defined in
         * RabbbitMQ */
        return newEnquiry;
    }


    @Override
    public List<Enquiry> listAllEnquiries() {
        return enquiryRepository.findAll();
    }


    @Override
    public Enquiry getEnquiryByCode(String enquiryCode) throws EnquiryNotFoundException {
        if(enquiryRepository.findById(enquiryCode).isEmpty()) {
            throw new EnquiryNotFoundException();
        }
        return enquiryRepository.findById(enquiryCode).get();
    }

}
