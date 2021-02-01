package com.goldys.enquiryservice.service;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;
import com.goldys.enquiryservice.repository.EnquiryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/*
 * This class is implementing the EnquiryService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
@CacheConfig(cacheNames="enquiry")
public class EnquiryServiceImpl implements EnquiryService {

    /*
     * Constructor Autowiring should be implemented for the EnquiryRepository
     * and RabbitTemplate.
     */
	private EnquiryRepository enquiryRepository;
	private RabbitTemplate rabbitTemplate;

	@Autowired
	 public EnquiryServiceImpl(EnquiryRepository enquiryRepository, RabbitTemplate rabbitTemplate) {
			super();
			this.enquiryRepository = enquiryRepository;
			this.rabbitTemplate = rabbitTemplate;
		}
    /*
     * Add a new enquiry.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again.
     */
	@CacheEvict(value = "enquiry",allEntries = true)
	@Override
	public Enquiry addNewEnquiry(Enquiry enquiry) {
		Enquiry savedEnquiry = enquiryRepository.save(enquiry);
		rabbitTemplate.convertAndSend("enquiry.new.queue",savedEnquiry.getEnquiryCode());
        return savedEnquiry;
    }


    /*
     * Retrieve all existing enquiries.
     * Caching should be implemented to reduce method calls.
     */

	@Cacheable(value="enquiry")
    @Override
    public List<Enquiry> listAllEnquiries() {
        return enquiryRepository.findAll();
    }


    /*
     * Retrieve an existing enquiry by it's enquiryCode. Throw EnquiryNotFoundException if the
     * enquiry with specified enquiryCode does not exist.
     * Caching should be implemented to reduce method calls.
     */
	@Cacheable(value="enquiry",key="#enquiryCode")
    @Override
    public Enquiry getEnquiryByCode(String enquiryCode) throws EnquiryNotFoundException {
    	Optional<Enquiry> enquiryExist = enquiryRepository.findById(enquiryCode);
		if(enquiryExist.isEmpty()) {
			throw new EnquiryNotFoundException();
		}
        return enquiryRepository.findById(enquiryCode).get();
    }

}
